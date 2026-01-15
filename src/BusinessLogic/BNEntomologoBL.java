package BusinessLogic.Services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

import BusinessLogic.Entities.*;
import BusinessLogic.Interface.IEntomologo;

import DataAccess.DAOs.*;
import DataAccess.DTOs.*;

import Infrastructure.AppException;
import Infrastructure.Tools.CMDColor;

public class BNEntomologoBL implements IEntomologo {

    private static final Set<String> VALID_ANTS  = Set.of("HLarva", "HSoldado");
    private static final Set<String> VALID_FOODS = Set.of("Nectarivoros", "Carnivoro"); // normalizado sin acento

    private final HormigaDAO hormigaDAO;
    private final HormigaAlimentoDAO hormigaAlimentoDAO;
    private final HormigaSuperHabilidadDAO hormigaSuperDAO;

    private final AlimentoDAO alimentoDAO;
    private final AlimentoTipoDAO alimentoTipoDAO;

    private final GenomaDAO genomaDAO;
    private final SuperHabilidadDAO superDAO;

    private final HormigaTipoDAO hormigaTipoDAO;
    private final EstadoDAO estadoDAO;

    public BNEntomologoBL() throws AppException {
        hormigaDAO = new HormigaDAO();
        alimentoDAO = new AlimentoDAO();
        alimentoTipoDAO = new AlimentoTipoDAO();

        hormigaAlimentoDAO = new HormigaAlimentoDAO();
        hormigaSuperDAO = new HormigaSuperHabilidadDAO();

        genomaDAO = new GenomaDAO();
        superDAO = new SuperHabilidadDAO();

        hormigaTipoDAO = new HormigaTipoDAO();
        estadoDAO = new EstadoDAO();
    }

    @Override
    public List<BNHormiga> etlAntNest(String pathFile) {
        List<BNHormiga> out = new ArrayList<>();
        List<String> tokens = readTokens(pathFile, ",");

        int larvaCount = 0, soldCount = 0;

        for (String raw : tokens) {
            String t = normalizeToken(raw);
            if (t.isBlank()) continue;

            // validar caso
            if (!VALID_ANTS.contains(t)) {
                printInvalid("AntNest", raw);
                continue;
            }
            printValid("AntNest", raw);

            try {
                // ids lookup
                int idTipo = hormigaTipoDAO.getIdByNombre(t);
                int idEstado = estadoDAO.getIdByNombre("VIVA");

                // genoma por defecto en ETL
                int idGenoma = genomaDAO.getIdByNombre(t.equals("HSoldado") ? "XX" : "X");

                String nombre = (t.equals("HSoldado"))
                        ? String.format("HSoldado-%03d", ++soldCount)
                        : String.format("HLarva-%03d", ++larvaCount);

                HormigaDTO dto = new HormigaDTO(
                        0, idTipo, idGenoma, idEstado,
                        nombre,
                        "Cargado por ETL (caso HSoldado)"
                );
                hormigaDAO.create(dto);

                // no tenemos el Id generado; recuperamos por nombre
                Integer idHormiga = getHormigaIdByNombre(nombre);

                // Instancia BL para devolver lista
                if (t.equals("HSoldado")) out.add(new BNHSoldado());
                else out.add(new BNHLarva());

                // si es HSoldado, asignar superhabilidad (XX + superSaltar)
                if (t.equals("HSoldado") && idHormiga != null) {
                    int idSuper = superDAO.getIdByNombre("superSaltar");
                    HormigaSuperHabilidadDTO hsh = new HormigaSuperHabilidadDTO(
                            idHormiga, idGenoma, idSuper, "HSoldado+XX => superSaltar"
                    );
                    hormigaSuperDAO.create(hsh);
                }

            } catch (Exception e) {
                // no rompas ETL por un registro
                System.out.println(CMDColor.RED + "[ETL-ERROR] " + e.getMessage() + CMDColor.RESET);
            }
        }
        return out;
    }

    @Override
    public List<Alimento> etlAntFood(String pathFile) {
        List<Alimento> out = new ArrayList<>();
        List<String> tokens = readTokens(pathFile, "-"); // el archivo está lleno de guiones

        int carCount = 0, necCount = 0;

        for (String raw : tokens) {
            String t = normalizeToken(raw);
            if (t.isBlank()) continue;

            // filtro del caso
            if (!VALID_FOODS.contains(t)) {
                printInvalid("AntFood", raw);
                continue;
            }
            printValid("AntFood", raw);

            try {
                String nombreTipoDB = t.equals("Carnivoro") ? "Carnívoro" : "Nectarívoros";
                int idTipo = alimentoTipoDAO.getIdByNombre(nombreTipoDB);

                String nombre = t.equals("Carnivoro")
                        ? String.format("Carnívoro-%03d", ++carCount)
                        : String.format("Nectarívoros-%03d", ++necCount);

                // insert alimento
                AlimentoDTO dto = new AlimentoDTO(idTipo, nombre, "Cargado por ETL (caso HSoldado)");
                alimentoDAO.create(dto);

                // devolver entidad BL
                if (t.equals("Carnivoro")) out.add(new Carnivoro());
                else out.add(new Nectarivoro());

            } catch (Exception e) {
                System.out.println(CMDColor.RED + "[ETL-ERROR] " + e.getMessage() + CMDColor.RESET);
            }
        }
        return out;
    }

    @Override
    public Alimento preparar(Alimento alimento) {
        // Entomólogo normal: no inyecta genoma
        System.out.println("[Preparado] -" + alimento.getTipoAlimento() + "-");
        return alimento;
    }

    @Override
    public BNHormiga alimentarAnt(BNHormiga hormiga, Alimento alimento) {
        // esto lo implementamos después del ETL (reglas del enunciado)
        return hormiga;
    }

    private List<String> readTokens(String pathFile, String splitBy) {
        try {
            String content = Files.readString(Paths.get(pathFile), StandardCharsets.UTF_8);
            // divide por splitBy y también por coma si viene mezclado
            String[] arr = content.split(Pattern.quote(splitBy));
            return Arrays.asList(arr);
        } catch (IOException e) {
            System.out.println(CMDColor.RED + "[ETL] No se pudo leer: " + pathFile + CMDColor.RESET);
            return List.of();
        }
    }

    private static String normalizeToken(String s) {
        if (s == null) return "";
        String x = s.replace("\t", " ").replace(",", " ").replace("\n", " ").replace("\r", " ");
        x = x.replace("–", "-").replace("—", "-");
        x = x.replaceAll("\\s+", " ").trim();
        x = x.replace("-", "").trim(); // quita guiones sueltos

        // quita acentos para comparar
        x = Normalizer.normalize(x, Normalizer.Form.NFD);
        x = x.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        // normaliza nombres con variaciones
        x = x.replace("Nectarivoros", "Nectarivoros");
        x = x.replace("Carnivoro", "Carnivoro");
        return x;
    }

    private void printValid(String src, String raw) {
        System.out.println(CMDColor.BLUE + "[OK][" + src + "] " + raw.trim() + CMDColor.RESET);
    }

    private void printInvalid(String src, String raw) {
        System.out.println(CMDColor.RED + "[X][" + src + "] " + raw.trim() + CMDColor.RESET);
    }

    private Integer getHormigaIdByNombre(String nombre) throws AppException {

        try {
            var conn = DataAccess.Helpers.DataHelperSQLiteDAO.class; 
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}

