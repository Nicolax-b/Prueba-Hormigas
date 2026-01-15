package BusinessLogic.Services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.Normalizer;
import java.util.*;

import BusinessLogic.Entities.*;
import BusinessLogic.Interface.IEntomologo;

import DataAccess.DAOs.*;
import DataAccess.DTOs.*;

import Infrastructure.AppException;
import Infrastructure.Tools.CMDColor;

public class BNEntomologo implements IEntomologo {

    // ===== Caso de estudio (HSoldado) =====
    private static final Set<String> BN_VALID_ANTS  = Set.of("HLarva", "HSoldado");
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

    public BNEntomologo() throws AppException {
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

        int BN_contLarva = 0;
        int BN_contSoldado = 0;

        for (String BN_raw : BN_tokens) {

            BN_loading("AntNest", BN_raw);

            String BN_token = BN_normalizeToken(BN_raw);
            if (BN_token.isBlank()) continue;

            if (!BN_VALID_ANTS.contains(BN_token)) {
                BN_invalid("AntNest", BN_raw);
                continue;
            }

            BN_valid("AntNest", BN_raw);

            try {
                Integer BN_idTipo   = BN_hormigaTipoDAO.getIdByNombre(BN_token);
                Integer BN_idEstado = BN_estadoDAO.getIdByNombre("VIVA");

                String  BN_genomaNombre = BN_token.equals("HSoldado") ? "XX" : "X";
                Integer BN_idGenoma     = BN_genomaDAO.getIdByNombre(BN_genomaNombre);

                if (BN_idTipo == null || BN_idEstado == null || BN_idGenoma == null) {
                    System.out.println(CMDColor.RED +
                        "[ETL][BD] Catálogo faltante: " + BN_token +
                        " / Genoma " + BN_genomaNombre + CMDColor.RESET);
                    continue;
                }

                String BN_nombre = BN_token.equals("HSoldado")
                        ? String.format("HSoldado-%03d", ++BN_contSoldado)
                        : String.format("HLarva-%03d", ++BN_contLarva);

                HormigaDTO BN_dtoHormiga = new HormigaDTO();
                BN_dtoHormiga.setIdHormigaTipo(BN_idTipo);
                BN_dtoHormiga.setIdGenoma(BN_idGenoma);
                BN_dtoHormiga.setIdEstado(BN_idEstado);
                BN_dtoHormiga.setNombre(BN_nombre);
                BN_dtoHormiga.setDescripcion("Cargado por ETL (caso HSoldado)");
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

                BN_resultado.add(
                        "HSoldado".equals(BN_token)
                        ? new BNHSoldado()
                        : new BNHLarva()
                );

            } catch (Exception BN_ex) {
                System.out.println(CMDColor.RED +
                        "[ETL][ERROR] " + BN_ex.getMessage() + CMDColor.RESET);
            }
        }

        return BN_resultado;
    }

    // ============================ ETL: AntFood ============================
    @Override
    public List<Alimento> etlAntFood(String BN_pathFile) {

        List<Alimento> BN_resultado = new ArrayList<>();
        List<String> BN_tokens = BN_readTokens(BN_pathFile);

        int BN_contCarn = 0;
        int BN_contNect = 0;

        for (String BN_raw : BN_tokens) {

            BN_loading("AntFood", BN_raw);

            String BN_token = BN_normalizeToken(BN_raw);
            if (BN_token.isBlank()) continue;

            BN_token = BN_token.replace("Nectarivoro", "Nectarivoros");

            if (!BN_VALID_FOODS.contains(BN_token)) {
                BN_invalid("AntFood", BN_raw);
                continue;
            }

            BN_valid("AntFood", BN_raw);

            try {
                String BN_nombreTipoBD = BN_token.equals("Carnivoro")
                        ? "Carnívoro"
                        : "Nectarívoros";

                Integer BN_idTipo = BN_alimentoTipoDAO.getIdByNombre(BN_nombreTipoBD);
                if (BN_idTipo == null) {
                    System.out.println(CMDColor.RED +
                        "[ETL][BD] No existe AlimentoTipo: " + BN_nombreTipoBD + CMDColor.RESET);
                    continue;
                }

                String BN_nombre = BN_token.equals("Carnivoro")
                        ? String.format("Carnívoro-%03d", ++BN_contCarn)
                        : String.format("Nectarívoros-%03d", ++BN_contNect);

                AlimentoDTO BN_dtoAlimento = new AlimentoDTO();
                BN_dtoAlimento.setIdAlimentoTipo(BN_idTipo);
                BN_dtoAlimento.setNombre(BN_nombre);
                BN_dtoAlimento.setDescripcion("Cargado por ETL (caso HSoldado)");
                BN_dtoAlimento.setEstado("A");

                BN_alimentoDAO.create(BN_dtoAlimento);

                BN_resultado.add(
                        BN_token.equals("Carnivoro")
                        ? new Carnivoro()
                        : new Nectarivoro()
                );

            } catch (Exception BN_ex) {
                System.out.println(CMDColor.RED +
                        "[ETL][ERROR] " + BN_ex.getMessage() + CMDColor.RESET);
            }
        }

        return BN_resultado;
    }

    // ============================ Interfaz ============================
    @Override
    public Alimento preparar(Alimento BN_alimento) {
        System.out.println("[Preparado] -" + BN_alimento.getTipoAlimento() + "-");
        return BN_alimento;
    }

    @Override
    public BNHormiga alimentarAnt(BNHormiga BN_hormiga, Alimento BN_alimento) {
        // Se implementa después (vida/muerte/evolución)
        return BN_hormiga;
    }

    // ============================ Helpers ============================
    private static void BN_loading(String BN_src, String BN_raw) {
        System.out.println("[Loading][" + BN_src + "] " +
                (BN_raw == null ? "" : BN_raw.trim()));
    }

    private static void BN_valid(String BN_src, String BN_raw) {
        System.out.println(CMDColor.BLUE +
                "[OK][" + BN_src + "] " + BN_raw.trim() + CMDColor.RESET);
    }

    private static void BN_invalid(String BN_src, String BN_raw) {
        System.out.println(CMDColor.RED +
                "[X][" + BN_src + "] " + BN_raw.trim() + CMDColor.RESET);
    }

    private static List<String> BN_readTokens(String BN_pathFile) {
        try {
            String BN_content = Files.readString(Paths.get(BN_pathFile), StandardCharsets.UTF_8);
            String[] BN_arr = BN_content.split("[,\\-\\n\\r\\t]+");
            return Arrays.asList(BN_arr);
        } catch (IOException BN_ex) {
            System.out.println(CMDColor.RED +
                    "[ETL] No se pudo leer: " + BN_pathFile + CMDColor.RESET);
            return List.of();
        }
    }

    private static String BN_normalizeToken(String BN_s) {
        if (BN_s == null) return "";
        String BN_x = BN_s.trim().replaceAll("\\s+", " ");

        BN_x = Normalizer.normalize(BN_x, Normalizer.Form.NFD);
        BN_x = BN_x.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        return BN_x.replace(" ", "");
    }
}
