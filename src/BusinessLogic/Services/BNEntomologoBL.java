package BusinessLogic.Services;

import BusinessLogic.Entities.*;
import BusinessLogic.Interface.IEntomologo;
import DataAccess.DAOs.*;
import DataAccess.DTOs.*;
import Infrastructure.AppException;
import Infrastructure.Tools.CMDColor;
import Infrastructure.Tools.CMDProgress;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.Normalizer;
import java.util.*;

public class BNEntomologoBL implements IEntomologo {

    private static final Set<String> BN_VALID_ANTS = Set.of("HLarva", "HSoldado");
    private static final Set<String> BN_VALID_FOODS = Set.of("Carnivoro", "Nectarivoros");

    private final HormigaDAO BN_hormigaDAO;
    private final AlimentoDAO BN_alimentoDAO;
    private final HormigaTipoDAO BN_hormigaTipoDAO;
    private final EstadoDAO BN_estadoDAO;
    private final GenomaDAO BN_genomaDAO;
    private final AlimentoTipoDAO BN_alimentoTipoDAO;
    private final SuperHabilidadDAO BN_superHabilidadDAO;
    private final HormigaAlimentoDAO BN_hormigaAlimentoDAO;
    private final HormigaSuperHabilidadDAO BN_hormigaSuperHabilidadDAO;

    public BNEntomologoBL() throws AppException {
        BN_hormigaDAO = new HormigaDAO();
        BN_alimentoDAO = new AlimentoDAO();
        BN_hormigaTipoDAO = new HormigaTipoDAO();
        BN_estadoDAO = new EstadoDAO();
        BN_genomaDAO = new GenomaDAO();
        BN_alimentoTipoDAO = new AlimentoTipoDAO();
        BN_superHabilidadDAO = new SuperHabilidadDAO();
        BN_hormigaAlimentoDAO = new HormigaAlimentoDAO();
        BN_hormigaSuperHabilidadDAO = new HormigaSuperHabilidadDAO();
    }

    // ============================ ETL: AntNest ============================
    @Override
    public List<BNHormiga> etlAntNest(String BN_pathFile) {
        List<BNHormiga> BN_resultado = new ArrayList<>();
        List<String> BN_tokens = BN_readTokens(BN_pathFile);
        int BN_total = BN_tokens.size(); // Total para calcular %

        int BN_contLarva = 0;
        int BN_contSoldado = 0;

        // Bucle con índice para la barra de progreso
        for (int i = 0; i < BN_total; i++) {
            String BN_raw = BN_tokens.get(i);

            // --- ACTUALIZAR BARRA ---
            CMDProgress.showProgressBar(i + 1, BN_total, "Cargando AntNest");
            // ------------------------

            String BN_token = BN_normalizeToken(BN_raw);
            if (BN_token.isBlank()) {
                continue;
            }

            if (!BN_VALID_ANTS.contains(BN_token)) {
                continue;
            }

            try {
                String BN_nombre = BN_token.equals("HSoldado")
                        ? String.format("HSoldado-%03d", ++BN_contSoldado)
                        : String.format("HLarva-%03d", ++BN_contLarva);

                Integer BN_idExistente = BN_hormigaDAO.getIdByNombre(BN_nombre);
                if (BN_idExistente != null) {
                    BN_resultado.add("HSoldado".equals(BN_token) ? new BNHSoldado() : new BNHLarva());
                    continue;
                }

                Integer BN_idTipo = BN_hormigaTipoDAO.getIdByNombre(BN_token);
                Integer BN_idEstado = BN_estadoDAO.getIdByNombre("VIVA");
                String BN_genomaNombre = BN_token.equals("HSoldado") ? "XX" : "X";
                Integer BN_idGenoma = BN_genomaDAO.getIdByNombre(BN_genomaNombre);

                if (BN_idTipo == null || BN_idEstado == null || BN_idGenoma == null) {
                    continue;
                }

                HormigaDTO BN_dtoHormiga = new HormigaDTO();
                BN_dtoHormiga.setIdHormigaTipo(BN_idTipo);
                BN_dtoHormiga.setIdGenoma(BN_idGenoma);
                BN_dtoHormiga.setIdEstado(BN_idEstado);
                BN_dtoHormiga.setNombre(BN_nombre);
                BN_dtoHormiga.setDescripcion("Cargado por ETL");
                BN_dtoHormiga.setEstado("A");

                BN_hormigaDAO.create(BN_dtoHormiga);

                Integer BN_idHormiga = BN_hormigaDAO.getIdByNombre(BN_nombre);
                if ("HSoldado".equals(BN_token) && BN_idHormiga != null) {
                    Integer BN_idSuper = BN_superHabilidadDAO.getIdByNombre("superSaltar");
                    if (BN_idSuper != null) {
                        HormigaSuperHabilidadDTO BN_hsh = new HormigaSuperHabilidadDTO();
                        BN_hsh.setIdHormiga(BN_idHormiga);
                        BN_hsh.setIdGenoma(BN_idGenoma);
                        BN_hsh.setIdSuperHabilidad(BN_idSuper);
                        BN_hsh.setDescripcion("HSoldado + XX => superSaltar");
                        BN_hsh.setEstado("A");
                        BN_hormigaSuperHabilidadDAO.create(BN_hsh);
                    }
                }
                BN_resultado.add("HSoldado".equals(BN_token) ? new BNHSoldado() : new BNHLarva());

            } catch (Exception BN_ex) {
                // Imprimir error saltando línea para no romper la barra
                System.out.println("\n" + CMDColor.RED + "[ETL][ERROR] " + BN_ex.getMessage() + CMDColor.RESET);
            }
        }
        return BN_resultado;
    }

    // ============================ ETL: AntFood ============================
    @Override
    public List<Alimento> etlAntFood(String BN_pathFile) {
        List<Alimento> BN_resultado = new ArrayList<>();
        List<String> BN_tokens = BN_readTokens(BN_pathFile);
        int BN_total = BN_tokens.size();

        for (int i = 0; i < BN_total; i++) {
            String BN_raw = BN_tokens.get(i);
            CMDProgress.showProgressBar(i + 1, BN_total, "Cargando AntFood");

            String BN_token = BN_normalizeToken(BN_raw);
            if (BN_token.isBlank()) {
                continue;
            }

            // CORRECCIÓN: Evitar "Nectarivoross"
            if (BN_token.equals("Nectarivoro")) {
                BN_token = "Nectarivoros";
            }

            if (!BN_VALID_FOODS.contains(BN_token)) {
                continue;
            }

            try {
                // ... resto de tu lógica de BD ...
                BN_resultado.add(BN_token.equals("Carnivoro") ? new Carnivoro() : new Nectarivoro());
            } catch (Exception e) {
                /* ... */ }
        }
        return BN_resultado;
    }

    // Actualiza alimentarAnt para permitir la evolución:
   @Override
public List<Alimento> etlAntFood(String BN_pathFile) {
    List<Alimento> BN_resultado = new ArrayList<>();
    List<String> BN_tokens = BN_readTokens(BN_pathFile);
    int BN_total = BN_tokens.size();
    Random BN_rand = new Random(); 

    for (int i = 0; i < BN_total; i++) {
        String BN_raw = BN_tokens.get(i);
        String BN_token = BN_normalizeToken(BN_raw);
        if (BN_token.isBlank()) continue;

        if (BN_token.equals("Nectarivoro")) BN_token = "Nectarivoros";
        if (!BN_VALID_FOODS.contains(BN_token)) continue;

        try {
            Alimento BN_comida = BN_token.equals("Carnivoro") ? new Carnivoro() : new Nectarivoro();
            
            // Subimos la probabilidad al 70% para que lo veas más seguido
            if (BN_rand.nextInt(100) < 70) { 
                int BN_tipoGeno = BN_rand.nextInt(3); 
                GenoAlimento g = switch (BN_tipoGeno) {
                    case 0 -> new X();
                    case 1 -> new XX();
                    default -> new XY();
                };
                BN_comida.setGenoAlimento(g);
                // ESTE PRINT es para que veas que el ETL SI asignó genoma
                System.out.println(CMDColor.YELLOW + "    [ETL] Alimento con Genoma: " + g.getClass().getSimpleName() + CMDColor.RESET);
            }

            BN_resultado.add(BN_comida);
        } catch (Exception e) { }
    }
    return BN_resultado;
}
    @Override
    public BNHormiga alimentarAnt(BNHormiga BN_hormiga, Alimento BN_alimento) {
        if (BN_hormiga == null || BN_alimento == null) {
            return BN_hormiga;
        }

        String BN_tipoHormiga = BN_hormiga.getClass().getSimpleName();
        String BN_tipoAlimento = BN_alimento.getClass().getSimpleName();
        String BN_genomaStr = (BN_alimento.getGenoAlimento() != null)
                ? BN_alimento.getGenoAlimento().getClass().getSimpleName()
                : "SIN GENOMA";

        System.out.println("=================================");
        System.out.println("[ALIMENTANDO]");
        System.out.println("Hormiga : " + BN_tipoHormiga);
        System.out.println("Alimento: " + BN_tipoAlimento + " (" + BN_genomaStr + ")");

        // LÓGICA DE EVOLUCIÓN: Larva + Carnívoro = Soldado
        if (BN_tipoHormiga.contains("Larva") && BN_tipoAlimento.contains("Carnivoro")) {
            System.out.println(CMDColor.BLUE + "[MUTACIÓN] ¡La larva ha evolucionado a SOLDADO!" + CMDColor.RESET);
            BN_hormiga = new BNHSoldado();
            // Si el alimento tiene genoma, se inyecta a la nueva hormiga
            if (BN_alimento.getGenoAlimento() != null) {
                BN_alimento.inyectar(BN_hormiga);
            }
            System.out.println("=================================");
            return BN_hormiga;
        }

        // Lógica de supervivencia normal
        boolean BN_vive = false;
        if (BN_tipoHormiga.contains("Soldado")) {
            BN_vive = BN_tipoAlimento.contains("Carnivoro");
        } else if (BN_tipoHormiga.contains("Larva")) {
            BN_vive = BN_tipoAlimento.contains("Nectarivoro");
        }

        if (!BN_vive) {
            System.out.println(CMDColor.RED + "[MUERE] Ingesta letal." + CMDColor.RESET);
            System.out.println("=================================");
            return null;
        }

        // Inyectar genoma si existe
        if (BN_alimento.getGenoAlimento() != null) {
            BN_alimento.inyectar(BN_hormiga);
            System.out.println(CMDColor.BLUE + "[VIVE] Genoma inyectado: " + BN_genomaStr + CMDColor.RESET);
        } else {
            System.out.println(CMDColor.GREEN + "[VIVE] Sin cambios genéticos." + CMDColor.RESET);
        }

        System.out.println("=================================");
        return BN_hormiga;
    }

    // ============================ Interfaz y Helpers ============================
    @Override
    public Alimento preparar(Alimento BN_alimento) {
        System.out.println("[Preparado] -" + BN_alimento.getTipoAlimento() + "-");
        return BN_alimento;
    }

    @Override
    public BNHormiga alimentarAnt(BNHormiga BN_hormiga, Alimento BN_alimento) {
        if (BN_hormiga == null || BN_alimento == null) {
            return BN_hormiga;
        }

        String BN_tipoHormiga = BN_hormiga.getClass().getSimpleName();
        String BN_tipoAlimento = BN_alimento.getClass().getSimpleName();

        System.out.println("=================================");
        System.out.println("[ALIMENTANDO]");
        System.out.println("Hormiga : " + BN_tipoHormiga);
        System.out.println("Alimento: " + BN_tipoAlimento);

        boolean BN_vive = false;
        if ("BNHSoldado".equals(BN_tipoHormiga)) {
            BN_vive = "Carnivoro".equals(BN_tipoAlimento);
        } else if ("BNHLarva".equals(BN_tipoHormiga)) {
            BN_vive = "Nectarivoro".equals(BN_tipoAlimento);
        }

        if (!BN_vive) {
            System.out.println(CMDColor.RED + "[MUERE]" + CMDColor.RESET);
            System.out.println("=================================");
            return BN_hormiga;
        }

        System.out.println(CMDColor.BLUE + "[VIVE]" + CMDColor.RESET);
        if ("BNHSoldado".equals(BN_tipoHormiga)) {
            System.out.println("[GENOMA] XX");
            System.out.println(CMDColor.BLUE + "[SUPERPODER ADQUIRIDO] superSaltar" + CMDColor.RESET);
        } else {
            System.out.println("[SIN SUPERPODER]");
        }
        System.out.println("=================================");
        return BN_hormiga;
    }

    private static List<String> BN_readTokens(String BN_pathFile) {
        try {
            String BN_content = Files.readString(Paths.get(BN_pathFile), StandardCharsets.UTF_8);
            String[] BN_arr = BN_content.split("[,\\-\\n\\r\\t]+");
            return Arrays.asList(BN_arr);
        } catch (IOException BN_ex) {
            System.out.println(CMDColor.RED + "[ETL] No se pudo leer: " + BN_pathFile + CMDColor.RESET);
            return List.of();
        }
    }

    private static String BN_normalizeToken(String BN_s) {
        if (BN_s == null) {
            return "";
        }
        String BN_x = BN_s.replace("\uFEFF", "");
        BN_x = BN_x.trim().replaceAll("\\s+", " ");
        BN_x = Normalizer.normalize(BN_x, Normalizer.Form.NFD);
        BN_x = BN_x.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        BN_x = BN_x.replaceAll("[^\\p{Alnum}]", "");
        return BN_x;
    }
}
