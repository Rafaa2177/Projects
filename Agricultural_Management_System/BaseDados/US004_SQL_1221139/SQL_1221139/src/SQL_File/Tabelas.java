package SQL_File;

import Sheets.*;

import java.sql.Date;
import java.util.ArrayList;

public class Tabelas {
    ArrayList<String> tabelaFabricante = new ArrayList<>();
    ArrayList<String> tabelaTipoProduto = new ArrayList<>();
    ArrayList<String> tabelaAplicacaoProduto = new ArrayList<>();
    ArrayList<String> tabelaFormato = new ArrayList<>();
    ArrayList<String> tabelaUnidade = new ArrayList<>();
    ArrayList<String> tabelaTipoAgricultura = new ArrayList<>();
    ArrayList<String> tabelaTipoPlantacao = new ArrayList<>();
    ArrayList<String> tabelaEspecie = new ArrayList<>();
    ArrayList<String> tabelaModo = new ArrayList<>();
    ArrayList<String> tabelaSubstancia = new ArrayList<>();
    ArrayList<String> tabelaFruto = new ArrayList<>();
    ArrayList<String> tabelaProduto = new ArrayList<>();
    ArrayList<Culturas> tabelaCultura = new ArrayList<>();
    ArrayList<String> tabelaReceitas = new ArrayList<>();
    public String getTabelas(){
        String tabelas = "";

        tabelas += Tabela_Fabricante() +"\n";
        tabelas += Tabela_Tipo_Produto() +"\n";
        tabelas += Tabela_Aplicacao_Produto() +"\n";
        tabelas += Tabela_Formato() +"\n";
        tabelas += Tabela_Unidades() +"\n";
        tabelas += Tabela_Tipo_Planta() +"\n";
        tabelas += Tabela_Especie() +"\n";
        tabelas += Tabela_Tipo_Agricultura() +"\n";
        tabelas += Tabele_Modo() +"\n";
        tabelas += Tabelas_Exploracao() +"\n";
        tabelas += Tabelas_Planta_Fruto() +"\n";
        tabelas += Tabela_Substancia() +"\n";
        tabelas += Tabela_Produto() +"\n";
        tabelas += Tabela_Ficha_Tecnica() +"\n";
        tabelas += Tabela_Setor() +"\n";
        tabelas += Tabela_Receita() +"\n";
        tabelas += Tabela_Receita_Produto() +"\n";
        tabelas += Tabela_Operacoes() +"\n";
        tabelas += Tabela_Ciclo() +"\n";
        tabelas += Tabela_Ciclo_Rega() +"\n";
        return tabelas;
    }
    public String Tabela_Fabricante(){
        String insert = "";
        int id = 0;
        for (FatorProducao ft : FatorProducao.getFatorProducao()) {
            if(!tabelaFabricante.contains(ft.getFabricante())){
                tabelaFabricante.add(ft.getFabricante());
                id++;
                insert += "INSERT INTO Fabricante (id ,nome) VALUES (" + id + ",'" + ft.getFabricante().toUpperCase() + "');\n";
            }
        }
        return insert;
    }
    public String Tabela_Tipo_Produto(){
        String insert = "";
        int id = 0;
        for (FatorProducao ft : FatorProducao.getFatorProducao()) {
            if(!tabelaTipoProduto.contains(ft.getTipo())){
                tabelaTipoProduto.add(ft.getTipo());
                id++;
                insert += "INSERT INTO Tipo_Produto (id, tipo) VALUES ("+ id +",'" + ft.getTipo().toUpperCase() + "');\n";
            }
        }
        return insert;
    }
    public String Tabela_Aplicacao_Produto(){
        String insert = "";
        int id = 0;
        for (FatorProducao ft : FatorProducao.getFatorProducao()) {
            if(!tabelaAplicacaoProduto.contains(ft.getAplicacao())){
                tabelaAplicacaoProduto.add(ft.getAplicacao());
                id++;
                insert += "INSERT INTO Aplicacao_Produto (id, aplicacao) VALUES ("+ id +",'" + ft.getAplicacao().toUpperCase() + "');\n";
            }
        }
        return insert;
    }
    public String Tabela_Formato(){
        String insert = "";
        int id = 0;
        for (FatorProducao ft : FatorProducao.getFatorProducao()) {
            if(!tabelaFormato.contains(ft.getFormato())){
                tabelaFormato.add(ft.getFormato());
                id++;
                insert += "INSERT INTO Formato (id, formato) VALUES ("+ id +",'" + ft.getFormato().toUpperCase() + "');\n";
            }
        }
        return insert;
    }
    public String Tabela_Unidades(){
        String insert = "";
        int id = 0;
        for (SetoresRega ft : SetoresRega.getSetoresRega()) {
            if(!tabelaUnidade.contains(ft.getUnidade()) && !ft.getUnidade().equals("null")){
                tabelaUnidade.add(ft.getUnidade());
                id++;
                insert += "INSERT INTO Unidade (id, nome) VALUES ("+ id +",'" + ft.getUnidade() + "');\n";
            }
        }
        for (ExploracaoAgricola ft : ExploracaoAgricola.getExploracaoAgricola()) {
            if(!tabelaUnidade.contains(ft.getUnidade()) && !ft.getUnidade().equals("null")){
                tabelaUnidade.add(ft.getUnidade());
                id++;
                insert += "INSERT INTO Unidade (id, nome) VALUES ("+ id +",'" + ft.getUnidade() + "');\n";
            }
        }
        for (Culturas ft : Culturas.getCulturas()) {
            if(!tabelaUnidade.contains(ft.getUnidade()) && !ft.getUnidade().equals("null")){
                tabelaUnidade.add(ft.getUnidade());
                id++;
                insert += "INSERT INTO Unidade (id, nome) VALUES ("+ id +",'" + ft.getUnidade() + "');\n";
            }
        }
        for (Operacoes ft : Operacoes.getOperacoes()) {
            if(!tabelaUnidade.contains(ft.getUnidade()) && !ft.getUnidade().equals("null")){
                tabelaUnidade.add(ft.getUnidade());
                id++;
                insert += "INSERT INTO Unidade (id, nome) VALUES ("+ id +",'" + ft.getUnidade() + "');\n";
            }
            if(!tabelaUnidade.contains(ft.getUnidadeArea()) && !ft.getUnidadeArea().equals("null")){
                tabelaUnidade.add(ft.getUnidadeArea());
                id++;
                insert += "INSERT INTO Unidade (id, nome) VALUES ("+ id +",'" + ft.getUnidadeArea() + "');\n";
            }
        }
        for (ReceitasFertirrega ft : ReceitasFertirrega.getReceitasFertirrega()) {
            if(!tabelaUnidade.contains(ft.getUnidade()) && !ft.getUnidade().equals("null")){
                tabelaUnidade.add(ft.getUnidade());
                id++;
                insert += "INSERT INTO Unidade (id, nome) VALUES ("+ id +",'" + ft.getUnidade() + "');\n";
            }
        }
        return insert;
    }
    public String Tabela_Tipo_Planta(){
        String insert = "";
        int id = 0;
        for (Plantas ft : Plantas.getPlantas()) {
            if(!tabelaTipoPlantacao.contains(ft.getTipo_plantacao())){
                tabelaTipoPlantacao.add(ft.getTipo_plantacao());
                id++;
                insert += "INSERT INTO Tipo_Plantacao (id, tipo) VALUES ("+ id +",'" + ft.getTipo_plantacao().toUpperCase() + "');\n";
            }
        }
        return insert;
    }
    public String Tabela_Especie(){
        String insert = "";
        int id = 0;
        for (Plantas ft : Plantas.getPlantas()) {
            if(!tabelaEspecie.contains(ft.getEspecie())){
                tabelaEspecie.add(ft.getEspecie());
                id++;
                insert += "INSERT INTO Especie (id, especie, nome) VALUES ("+ id +",'" + ft.getEspecie().toUpperCase() + "','" + ft.getNome().toUpperCase() + "');\n";
            }
        }
        return insert;
    }
    public String Tabela_Tipo_Agricultura(){
        String insert = "";
        int id = 0;
        for (Operacoes ft : Operacoes.getOperacoes()) {
            if(!tabelaTipoAgricultura.contains(ft.getOperacao()) && !ft.getOperacao().equals("null") && !ft.getOperacao().equals("Rega") && !ft.getOperacao().equals("Fertirrega") && !ft.getOperacao().equals("Colheita") && !ft.getOperacao().equals("Fertirrega") && !ft.getOperacao().equals("Fertilização") && !ft.getOperacao().equals("Aplicação fitofármaco") && !ft.getOperacao().equals("Semeadura") && !ft.getOperacao().equals("Plantação") && !ft.getOperacao().equals("Mobilização de Solo")) {
                tabelaTipoAgricultura.add(ft.getOperacao());
                id++;
                insert += "INSERT INTO Tipo_Agricultura (id, tipo) VALUES ("+ id +",'" + ft.getOperacao().toUpperCase() + "');\n";
            }
        }
        return insert;
    }
    public String Tabele_Modo(){
        String insert = "";
        int id = 0;
        for (Operacoes ft : Operacoes.getOperacoes()) {
            if(!tabelaModo.contains(ft.getModo()) && !ft.getModo().equals("null")){
                tabelaModo.add(ft.getModo());
                id++;
                insert += "INSERT INTO Modo (id, modo) VALUES ("+ id +",'" + ft.getModo().toUpperCase() + "');\n";
            }
        }
        return insert;
    }
    public String Tabelas_Exploracao(){
        String insertInstalacao = "";
        String insertParcela = "";
        String insertMoinho = "";
        String insertGaragem = "";
        String insertSistemaRega = "";
        String insertArmazem = "";
        String insert = "";
        int unidade = 0, tipoExploracaoid = 0;
        for (ExploracaoAgricola ft : ExploracaoAgricola.getExploracaoAgricola()) {
            // Parcela
            if (ft.getTipo().equals("Parcela")) {
                insertInstalacao += "INSERT INTO Instalacao (id, designacao) VALUES (" + ft.getId() + ",'" + ft.getDesignacao().toUpperCase() + "');\n";
                for (String s : tabelaUnidade) {
                    if (ft.getUnidade().equals(s)) {
                        unidade = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                insertParcela += "INSERT INTO Parcela (Instalacaoid, area, Unidadeid) VALUES (" + ft.getId() + "," + ft.getArea() + "," + unidade + ");\n";
            }
            // Armazém
            if (ft.getTipo().equals("Armazém")) {
                insertInstalacao += "INSERT INTO Instalacao (id, designacao) VALUES (" + ft.getId() + ",'" + ft.getDesignacao().toUpperCase() + "');\n";
                for (String s : tabelaUnidade) {
                    if (ft.getUnidade().equals(s)) {
                        unidade = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                insertArmazem += "INSERT INTO Armazem (Instalacaoid, area, Unidadeid) VALUES (" + ft.getId() + "," + ft.getArea() + "," + unidade + ");\n";
            }
            // Garagem
            if (ft.getTipo().equals("Garagem")) {
                insertInstalacao += "INSERT INTO Instalacao (id, designacao) VALUES (" + ft.getId() + ",'" + ft.getDesignacao().toUpperCase() + "');\n";
                for (String s : tabelaUnidade) {
                    if (ft.getUnidade().equals(s)) {
                        unidade = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                insertGaragem += "INSERT INTO Garagem (Instalacaoid, area, Unidadeid) VALUES (" + ft.getId() + "," + ft.getArea() + "," + unidade + ");\n";
            }
            // Sistema de Rega
            if (ft.getTipo().equals("Rega")) {
                insertInstalacao += "INSERT INTO Instalacao (id, designacao) VALUES (" + ft.getId() + ",'" + ft.getDesignacao().toUpperCase() + "');\n";
                for (String s : tabelaUnidade) {
                    if (ft.getUnidade().equals(s)) {
                        unidade = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                insertSistemaRega += "INSERT INTO Sistema_Rega (Instalacaoid, area, Unidadeid) VALUES (" + ft.getId() + "," + ft.getArea() + "," + unidade + ");\n";
            }
            // Moinho
            if (ft.getTipo().equals("Moinho")) {
                insertInstalacao += "INSERT INTO Instalacao (id, designacao) VALUES (" + ft.getId() + ",'" + ft.getDesignacao().toUpperCase() + "');\n";
                insertMoinho += "INSERT INTO Moinho (Instalacaoid) VALUES (" + ft.getId() + ");\n";
            }
        }
        insert = insertInstalacao + insertParcela + insertMoinho + insertGaragem + insertArmazem + insertSistemaRega;
        return insert;
    }
    public String Tabelas_Planta_Fruto(){
        String insertPlanta = "";
        String insertFruto = "";
        String insert = "";
        int especieid = 0, tipo_plantacaoid = 0, id = 0, idFruto = 0;
        for(Plantas ft : Plantas.getPlantas()){
            for (String s : tabelaEspecie) {
                if(ft.getEspecie().equals(s)) {
                    especieid = tabelaEspecie.indexOf(s)+1;
                }
            }
            for (String s : tabelaTipoPlantacao) {
                if(ft.getTipo_plantacao().equals(s)) {
                    tipo_plantacaoid = tabelaTipoPlantacao.indexOf(s)+1;
                }
            }
            if(!tabelaFruto.contains(ft.getFruto())){
                tabelaFruto.add(ft.getFruto());
            }
            id++;
            insertPlanta += "INSERT INTO Planta (id, variedade, EspecieId, Tipo_Plantacaoid) VALUES ("+ id +",'" + ft.getVariedade().toUpperCase() + "'," + especieid + "," + tipo_plantacaoid + ");\n";
            idFruto++;
            insertFruto += "INSERT INTO Fruto (id, PlantaId, fruto) VALUES (" + idFruto + ", " + id + ",'" + ft.getFruto().toUpperCase() + "');\n";
        }
        insert = insertPlanta + insertFruto;
        return insert;
    }
    public String Tabela_Substancia(){
        String insert = "";
        int id = 0;
        for (FatorProducao ft : FatorProducao.getFatorProducao()) {
            if(!tabelaSubstancia.contains(ft.getC1()) && !ft.getC1().equals("null")){
                tabelaSubstancia.add(ft.getC1());
                id++;
                insert += "INSERT INTO Substancia (id, nome) VALUES ("+ id +",'" + ft.getC1() + "');\n";
            }
            if(!tabelaSubstancia.contains(ft.getC2()) && !ft.getC2().equals("null")){
                tabelaSubstancia.add(ft.getC2());
                id++;
                insert += "INSERT INTO Substancia (id, nome) VALUES ("+ id +",'" + ft.getC2() + "');\n";
            }
            if(!tabelaSubstancia.contains(ft.getC3()) && !ft.getC3().equals("null")){
                tabelaSubstancia.add(ft.getC3());
                id++;
                insert += "INSERT INTO Substancia (id, nome) VALUES ("+ id +",'" + ft.getC3() + "');\n";
            }
            if(!tabelaSubstancia.contains(ft.getC4()) && !ft.getC4().equals("null")){
                tabelaSubstancia.add(ft.getC4());
                id++;
                insert += "INSERT INTO Substancia (id, nome) VALUES ("+ id +",'" + ft.getC4() + "');\n";
            }
            if(!tabelaSubstancia.contains(ft.getC5()) && !ft.getC5().equals("null")){
                tabelaSubstancia.add(ft.getC5());
                id++;
                insert += "INSERT INTO Substancia (id, nome) VALUES ("+ id +",'" + ft.getC5() + "');\n";
            }
            if(!tabelaSubstancia.contains(ft.getC6()) && !ft.getC6().equals("null")){
                tabelaSubstancia.add(ft.getC6());
                id++;
                insert += "INSERT INTO Substancia (id, nome) VALUES ("+ id +",'" + ft.getC6() + "');\n";
            }
            if(!tabelaSubstancia.contains(ft.getC7()) && !ft.getC7().equals("null")){
                tabelaSubstancia.add(ft.getC7());
                id++;
                insert += "INSERT INTO Substancia (id, nome) VALUES ("+ id +",'" + ft.getC7() + "');\n";
            }
            if(!tabelaSubstancia.contains(ft.getC8()) && !ft.getC8().equals("null")){
                tabelaSubstancia.add(ft.getC8());
                id++;
                insert += "INSERT INTO Substancia (id, nome) VALUES ("+ id +",'" + ft.getC8() + "');\n";
            }
        }
        return insert;
    }
    public String Tabela_Produto(){
        String insert = "";
        int fabricante = 0, tipo_produto = 0, aplicacao_produto = 0, formato = 0, id = 0;
        for (FatorProducao ft : FatorProducao.getFatorProducao()) {
            if (!tabelaProduto.contains(ft.getDesignacao())) {
                tabelaProduto.add(ft.getDesignacao());
            }
            for (String s : tabelaFabricante) {
                if(ft.getFabricante().equals(s)) {
                    fabricante = tabelaFabricante.indexOf(s)+1;
                }
            }
            for (String s : tabelaTipoProduto) {
                if(ft.getTipo().equals(s)) {
                    tipo_produto = tabelaTipoProduto.indexOf(s)+1;
                }
            }
            for (String s : tabelaAplicacaoProduto) {
                if(ft.getAplicacao().equals(s)) {
                    aplicacao_produto = tabelaAplicacaoProduto.indexOf(s)+1;
                }
            }
            for (String s : tabelaFormato) {
                if(ft.getFormato().equals(s)) {
                    formato = tabelaFormato.indexOf(s)+1;
                }
            }

            id++;
            insert += "INSERT INTO Produto (id, designacao, Fabricanteid, Tipo_Produtoid, Aplicacaoid, Formatoid) VALUES (" + id + ",'" + ft.getDesignacao().toUpperCase() + "'," + fabricante + "," + tipo_produto + "," + aplicacao_produto + "," + formato + ");\n";
        }
        return insert;
    }
    public String Tabela_Ficha_Tecnica() {
        String insert = "";
        int produtoId = 0, substanciaId;
        for (FatorProducao ft : FatorProducao.getFatorProducao()) {
            produtoId++;
            for (String s : tabelaSubstancia) {
                if (ft.getC1().equals(s)) {
                    substanciaId = tabelaSubstancia.indexOf(s) + 1;
                    insert += "INSERT INTO Ficha_Tecnica (Produtoid, Substanciaid, quantidade) VALUES (" + produtoId + "," + substanciaId + ",'" + ft.getPerc1() + "');\n";
                }
                if (ft.getC2().equals(s)) {
                    substanciaId = tabelaSubstancia.indexOf(s) + 1;
                    insert += "INSERT INTO Ficha_Tecnica (Produtoid, Substanciaid, quantidade) VALUES (" + produtoId + "," + substanciaId + ",'" + ft.getPerc2() + "');\n";
                }
                if (ft.getC3().equals(s)) {
                    substanciaId = tabelaSubstancia.indexOf(s) + 1;
                    insert += "INSERT INTO Ficha_Tecnica (Produtoid, Substanciaid, quantidade) VALUES (" + produtoId + "," + substanciaId + ",'" + ft.getPerc3() + "');\n";
                }
                if (ft.getC4().equals(s)) {
                    substanciaId = tabelaSubstancia.indexOf(s) + 1;
                    insert += "INSERT INTO Ficha_Tecnica (Produtoid, Substanciaid, quantidade) VALUES (" + produtoId + "," + substanciaId + ",'" + ft.getPerc4() + "');\n";
                }
                if (ft.getC5().equals(s)) {
                    substanciaId = tabelaSubstancia.indexOf(s) + 1;
                    insert += "INSERT INTO Ficha_Tecnica (Produtoid, Substanciaid, quantidade) VALUES (" + produtoId + "," + substanciaId + ",'" + ft.getPerc5() + "');\n";
                }
                if (ft.getC6().equals(s)) {
                    substanciaId = tabelaSubstancia.indexOf(s) + 1;
                    insert += "INSERT INTO Ficha_Tecnica (Produtoid, Substanciaid, quantidade) VALUES (" + produtoId + "," + substanciaId + ",'" + ft.getPerc6() + "');\n";
                }
                if (ft.getC7().equals(s)) {
                    substanciaId = tabelaSubstancia.indexOf(s) + 1;
                    insert += "INSERT INTO Ficha_Tecnica (Produtoid, Substanciaid, quantidade) VALUES (" + produtoId + "," + substanciaId + ",'" + ft.getPerc7() + "');\n";
                }
                if (ft.getC8().equals(s)) {
                    substanciaId = tabelaSubstancia.indexOf(s) + 1;
                    insert += "INSERT INTO Ficha_Tecnica (Produtoid, Substanciaid, quantidade) VALUES (" + produtoId + "," + substanciaId + ",'" + ft.getPerc8() + "');\n";
                }
            }

        }
        return insert;
    }
    public String Tabela_Setor() {
        String insert = "";
        int unidadeid = 0;
        for(SetoresRega ft : SetoresRega.getSetoresRega()){
            String dataInicial = "";
            String dataFinal = "";
            for (String s : tabelaUnidade) {
                if (ft.getUnidade().equals(s)) {
                    unidadeid = tabelaUnidade.indexOf(s) + 1;
                }
            }
            dataInicial = ft.getData_inicio().replaceAll("T00:00", "");
            dataInicial = "to_date('"+ dataInicial + "','YYYY-MM-DD')";

            if (!ft.getData_fim().equals("null")){
                dataFinal = ft.getData_fim().replaceAll("T00:00", "");
                dataFinal = "to_date('"+ dataFinal + "','YYYY-MM-DD')";
                insert += "INSERT INTO Setor (id, data_inicial, data_final, quantidade, Unidadeid, Sistema_RegaInstalacaoid) VALUES (" + ft.getId() + "," + dataInicial + "," + dataFinal + "," + ft.getQuantidade() + "," + unidadeid + "," + ft.getSistema() + ");\n";
            }
            else    insert += "INSERT INTO Setor (id, data_inicial, data_final, quantidade, Unidadeid, Sistema_RegaInstalacaoid) VALUES (" + ft.getId() + "," + dataInicial + ",null ," + ft.getQuantidade() + "," + unidadeid + "," + ft.getSistema() + ");\n";
        }
        return insert;
    }
    public String Tabela_Receita() {
        String insert = "";
        for(ReceitasFertirrega ft : ReceitasFertirrega.getReceitasFertirrega()){
            if(!tabelaReceitas.contains(ft.getId())){
                tabelaReceitas.add(ft.getId());
                insert += "INSERT INTO Receita (id) VALUES (" + ft.getId() + ");\n";
            }
        }
        return insert;
    }
    public String Tabela_Receita_Produto() {
        String insert = "";
        int produtoId = 0, unidadeid = 0;
        for(ReceitasFertirrega ft : ReceitasFertirrega.getReceitasFertirrega()){
            for (String s : tabelaProduto) {
                if(ft.getProduto().equals(s)) {
                    produtoId = tabelaProduto.indexOf(s)+1;
                }
            }
            for (String s : tabelaUnidade) {
                if (ft.getUnidade().equals(s)) {
                    unidadeid = tabelaUnidade.indexOf(s) + 1;
                }
            }
            insert += "INSERT INTO Receita_Produto (Receitaid, Produtoid, quantidade, Unidadeid) VALUES (" + ft.getId() + "," + produtoId + "," + ft.getQuantidade() + "," + unidadeid + ");\n";
        }
        return insert;
    }
    public String Tabela_Operacoes() {
        String insert = "";
        String insertOperacoes = "";
        String insertColheitas = "";
        String insertRegas = "";
        String insertFertirrega = "";
        String insertFertilizacao = "";
        String insertAplicacao = "";
        String insertSemeadura= "";
        String insertPlantacao = "";
        String insertMobilizacao = "";
        String insertOutrasOperacoes = "";
        String data = "";

        int id = 0;
        ArrayList<String> plantas = new ArrayList<String>();
        String nomeVariedade = "";
        for(Plantas planta : Plantas.getPlantas()){
            nomeVariedade = planta.getNome()+planta.getVariedade();
            nomeVariedade = nomeVariedade.replace(" ","");
            nomeVariedade = nomeVariedade.toUpperCase();
            plantas.add(nomeVariedade);
        }
        for (Operacoes ft : Operacoes.getOperacoes()) {
            int unidadeid = 0, tipo_agriculturaid = 0, frutoid = 0, modoid = 0, produtoid = 0, plantaid = 0;
            // Operação Geral
            id++;
            for (String s : tabelaUnidade) {
                if (ft.getUnidade().equals(s)) {
                    unidadeid = tabelaUnidade.indexOf(s) + 1;
                }
            }
            /*for (String s : tabelaTipoOperacao) {
                if (ft.getOperacao().equals(s)) {
                    tipo_operacaoid = tabelaTipoOperacao.indexOf(s) + 1;
                }
            }*/
            data = ft.getData().replaceAll("T00:00", "");
            data = "to_date('"+ data + "','YYYY-MM-DD')";

            insertOperacoes += "INSERT INTO Operacoes (data, ParcelaInstalacaoid) VALUES (" + data + "," + ft.getId() + ");\n";
            // Colheita
            if (ft.getOperacao().equals("Colheita")) {
                for (String s : tabelaFruto) {
                    if (ft.getFruto().equalsIgnoreCase(s)) {
                        frutoid = tabelaFruto.indexOf(s) + 1;
                    }
                }
                insertColheitas += "INSERT INTO Colheita (Operacoesid, Frutoid, quantidade, Unidadeid) VALUES (" + id + "," + frutoid + "," + ft.getQtt() + "," + unidadeid + ");\n";
            }
            // Rega
            else if (ft.getOperacao().equals("Rega")) {
                String horario = "";
                horario = ft.getHorario().replaceAll("1899-12-31T", "");
                insertRegas += "INSERT INTO Rega (Operacoesid, hora, Setorid, quantidade, Unidadeid) VALUES (" + id + ",TO_DATE('" + horario + "','HH24:MI')," + ft.getSetor() + "," + ft.getQtt() + "," + unidadeid + ");\n";
            }
            // Fertirrega
            else if (ft.getOperacao().equals("Fertirrega")) {
                String horario = "";
                horario = ft.getHorario().replaceAll("1899-12-31T", "");
                insertRegas += "INSERT INTO Rega (Operacoesid, hora, Setorid, quantidade, Unidadeid) VALUES (" + id + ",TO_DATE('" + horario + "','HH24:MI')," + ft.getSetor() + "," + ft.getQtt() + "," + unidadeid + ");\n";
                insertFertirrega += "INSERT INTO Fertirrega (RegaOperacoesid, Receitaid) VALUES (" + id + "," + ft.getReceita() + ");\n";
            }
            // Fertilização
            else if (ft.getOperacao().equals("Fertilização")) {
                int verify = 0;
                for (String s : tabelaModo) {
                    if (ft.getModo().equals(s)) {
                        modoid = tabelaModo.indexOf(s) + 1;
                    }
                }
                for (String s : tabelaProduto) {
                    if (ft.getFatorProducao().equals(s)) {
                        produtoid = tabelaProduto.indexOf(s) + 1;
                    }
                }
                for (String s : tabelaUnidade) {
                    if (ft.getUnidadeArea().equals(s)) {
                        unidadeid = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                String plantaOperacao = ft.getCultura().replaceAll(" ", "");
                plantaOperacao = plantaOperacao.toUpperCase();

                if(ft.getArea().equals("null") && ft.getCultura().equals("null")) {
                    verify = 1;
                }
                else if (!ft.getArea().equals("null") && ft.getCultura().equals("null")) {
                    verify = 2;
                }
                else if (!ft.getCultura().equals("null")) {
                    for (String s : plantas) {
                        if (plantaOperacao.equals(s.replaceAll("[\s\u00A0]+$", ""))) {
                            verify = 3;
                            plantaid = plantas.indexOf(s) + 1;
                        }
                    }
                }

                if(verify == 1) {
                    insertFertilizacao += "INSERT INTO Fertilizacao (Operacoesid, Modoid, Produtoid, area, unidadeArea, Plantaid, quantidade,  Unidadeid) VALUES (" + id + "," + modoid + "," + produtoid + ", null, null, null," + ft.getQtt() + "," + unidadeid + ");\n";
                }
                else if (verify == 2) {
                    insertFertilizacao += "INSERT INTO Fertilizacao (Operacoesid, Modoid, Produtoid, area, unidadeArea, Plantaid, quantidade,  Unidadeid) VALUES (" + id + "," + modoid + "," + produtoid + "," + ft.getArea() + ",'" + ft.getUnidadeArea() + "', null," + ft.getQtt() + "," + unidadeid + ");\n";
                }
                else if (verify == 3) {
                    insertFertilizacao += "INSERT INTO Fertilizacao (Operacoesid, Modoid, Produtoid, area, unidadeArea, Plantaid, quantidade,  Unidadeid) VALUES (" + id + "," + modoid + "," + produtoid + ", null, null," + plantaid + "," + ft.getQtt() + "," + unidadeid + ");\n";
                }
            }
            // Aplicação Fitofármaco
            else if (ft.getOperacao().equals("Aplicação fitofármaco")) {
                for (String s : tabelaProduto) {
                    if (ft.getFatorProducao().equals(s)) {
                        produtoid = tabelaProduto.indexOf(s) + 1;
                    }
                }
                String plantaOperacao = ft.getCultura().replaceAll(" ", "");
                plantaOperacao = plantaOperacao.toUpperCase();
                for (String s : plantas) {
                    if (plantaOperacao.equals(s.replaceAll("[\s\u00A0]+$", ""))) {
                        plantaid = plantas.indexOf(s) + 1;
                    }
                }
                if (plantaid != 0) {
                    insertAplicacao += "INSERT INTO Aplicacao (Operacoesid, Produtoid, Plantaid, quantidade, Unidadeid) VALUES (" + id + "," + produtoid + "," + plantaid + "," + ft.getQtt() + "," + unidadeid + ");\n";
                }
            }
            // Semeadura
            else if (ft.getOperacao().equals("Semeadura")) {
                for (String s : tabelaUnidade) {
                    if (ft.getUnidadeArea().equals(s)) {
                        unidadeid = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                String plantaOperacao = ft.getCultura().replaceAll(" ", "");
                plantaOperacao = plantaOperacao.toUpperCase();
                for (String s : plantas) {
                    if (plantaOperacao.equals(s.replaceAll("[\s\u00A0]+$", ""))) {
                        plantaid = plantas.indexOf(s) + 1;
                    }
                }
                if(plantaid != 0) {
                    insertSemeadura += "INSERT INTO Semeadura (Operacoesid, area, unidadeArea, Plantaid, quantidade, Unidadeid) VALUES (" + id + "," + ft.getArea() + ",'" + ft.getUnidadeArea() + "'," + plantaid + "," + ft.getQtt() + "," + unidadeid + ");\n";
                }
            }
            // Plantação
            else if (ft.getOperacao().equals("Plantação")) {
                for (String s : tabelaUnidade) {
                    if (ft.getUnidadeArea().equals(s)) {
                        unidadeid = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                String plantaOperacao = ft.getCultura().replaceAll(" ", "");
                plantaOperacao = plantaOperacao.toUpperCase();
                for (String s : plantas) {
                    if (plantaOperacao.equals(s.replaceAll("[\s\u00A0]+$", ""))) {
                        plantaid = plantas.indexOf(s) + 1;
                    }
                }
                if(plantaid != 0) {
                    if (!ft.getCompasso().equals("null")) {
                        insertPlantacao += "INSERT INTO Plantacao (Operacoesid, Plantaid, quantidade, Unidadeid, compasso, distancia) VALUES (" + id + "," + plantaid + "," + ft.getQtt() + "," + unidadeid + "," + ft.getCompasso() + "," + ft.getDistancia() + ");\n";

                    } else {
                        insertPlantacao += "INSERT INTO Plantacao (Operacoesid, Plantaid, quantidade, Unidadeid, compasso, distancia) VALUES (" + id + "," + plantaid + "," + ft.getQtt() + "," + unidadeid + ", null, null);\n";

                    }
                }
            }
            // Mobilização do solo
            else if (ft.getOperacao().equals("Mobilização de Solo")) {
                for (String s : tabelaUnidade) {
                    if (ft.getUnidadeArea().equals(s)) {
                        unidadeid = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                insertMobilizacao += "INSERT INTO MobilizacaoSolo (Operacoesid, quantidade, Unidadeid) VALUES (" + id + "," + ft.getQtt() + "," + unidadeid + ");\n";
            }
            // Outras Operações
            else {
                String plantaOperacao = ft.getCultura().replaceAll(" ", "");
                plantaOperacao = plantaOperacao.toUpperCase();
                for (String s : plantas) {
                    if (plantaOperacao.equals(s.replaceAll("[\s\u00A0]+$", ""))) {
                        plantaid = plantas.indexOf(s) + 1;
                    }
                }
                for (String s : tabelaTipoAgricultura) {
                    if (ft.getOperacao().equals(s)) {
                        tipo_agriculturaid = tabelaTipoAgricultura.indexOf(s) + 1;
                    }
                }
                for (String s : tabelaUnidade) {
                    if (ft.getUnidadeArea().equals(s)) {
                        unidadeid = tabelaUnidade.indexOf(s) + 1;
                    }
                }
                if(plantaid != 0) {
                    insertOutrasOperacoes += "INSERT INTO Agricultura (Operacoesid, Plantaid, quantidade, Unidadeid, Tipo_Agriculturaid) VALUES (" + id + "," + plantaid + "," + ft.getQtt() + "," + unidadeid + "," + tipo_agriculturaid + ");\n";
                }
            }
        }
        insert = insertOperacoes + insertColheitas + insertRegas + insertFertirrega + insertFertilizacao + insertAplicacao + insertSemeadura + insertPlantacao + insertMobilizacao + insertOutrasOperacoes;
        return insert;
    }
    public String Tabela_Ciclo() {
        String insert = "";
        int id = 0;
        ArrayList<String> plantas = new ArrayList<String>();
        String nomeVariedade = "";
        for(Plantas planta : Plantas.getPlantas()){
            nomeVariedade = planta.getNome()+planta.getVariedade();
            nomeVariedade = nomeVariedade.replace(" ","");
            nomeVariedade = nomeVariedade.toUpperCase();
            plantas.add(nomeVariedade);
        }
        for (Culturas ft : Culturas.getCulturas()) {
            String dataInicial = "";
            String dataFinal = "";
            tabelaCultura.add(ft);
            int unidadeid = 0, plantaid = 0;
            id++;
            for (String s : tabelaUnidade) {
                if (ft.getUnidade().equals(s)) {
                    unidadeid = tabelaUnidade.indexOf(s) + 1;
                }
            }
            String plantaOperacao = ft.getCultura().replaceAll(" ", "");
            plantaOperacao = plantaOperacao.toUpperCase();
            for (String s : plantas) {
                if (plantaOperacao.equals(s.replaceAll("[\s\u00A0]+$", ""))) {
                    plantaid = plantas.indexOf(s) + 1;
                }
            }
            dataInicial = ft.getDataInicial().replaceAll("T00:00", "");
            dataInicial = "to_date('"+ dataInicial + "','YYYY-MM-DD')";

            dataFinal = ft.getDataFinal().replaceAll("T00:00", "");
            dataFinal = "to_date('"+ dataFinal + "','YYYY-MM-DD')";
            if(ft.getDataFinal().equals("null")) {
                dataFinal = "null";
            }
            if (plantaid != 0) {
                insert += "INSERT INTO Ciclo (id, data_inicial, data_final, qtt, Unidadeid, Plantaid, ParcelaInstalacaoid) VALUES (" + id + "," + dataInicial + "," + dataFinal + "," + ft.getQtt() + "," + unidadeid + "," + plantaid + "," + ft.getId() + ");\n";
            }
        }
        return insert;
    }
    public String Tabela_Ciclo_Rega() {
        String insert = "";
        for (CicloRega ft : CicloRega.getCicloRega()) {
            int id = 0;
            String dataInicial2 = "";
            String dataFinal2 = "";
            for (Culturas s : tabelaCultura) {
                if (ft.getIdParcela().equals(s.getId()) && ft.getCultura().equals(s.getCultura())){
                    String dataInicial = ft.getDataInicial().replaceAll("T00:00", "");
                    Date dataInicialDate = Date.valueOf(dataInicial);

                    String dataInicialCultura = s.getDataInicial().replaceAll("T00:00", "");
                    Date dataInicialCulturaDate = Date.valueOf(dataInicialCultura);

                    if(dataInicialDate.after(dataInicialCulturaDate) || dataInicialDate.equals(dataInicialCulturaDate)){
                        if(ft.getDataFinal().equals("null") && s.getDataFinal().equals("null")){
                           id = tabelaCultura.indexOf(s) + 1;
                        }
                        else if (ft.getDataFinal().equals("null") && !s.getDataFinal().equals("null")){
                            String dataFinalCultura = s.getDataFinal().replaceAll("T00:00", "");
                            Date dataFinalCulturaDate = Date.valueOf(dataFinalCultura);
                            if (dataInicialDate.before(dataFinalCulturaDate) || dataInicialDate.equals(dataFinalCulturaDate)) {
                                id = tabelaCultura.indexOf(s) + 1;
                            }
                        }
                        else if (!ft.getDataFinal().equals("null") && s.getDataFinal().equals("null")){
                            id = tabelaCultura.indexOf(s) + 1;
                        }
                        else if (!ft.getDataFinal().equals("null") && !s.getDataFinal().equals("null")){
                            String dataFinal = ft.getDataFinal().replaceAll("T00:00", "");
                            Date dataFinalDate = Date.valueOf(dataFinal);

                            String dataFinalCultura = s.getDataFinal().replaceAll("T00:00", "");
                            Date dataFinalCulturaDate = Date.valueOf(dataFinalCultura);

                            if (dataInicialDate.after(dataInicialCulturaDate) || dataInicialDate.equals(dataInicialCulturaDate) && dataFinalDate.before(dataFinalCulturaDate) || dataFinalDate.equals(dataFinalCulturaDate)) {
                                id = tabelaCultura.indexOf(s) + 1;
                            }
                        }
                    }
                }
            }
            dataInicial2 = ft.getDataInicial().replaceAll("T00:00", "");
            dataInicial2 = "to_date('"+ dataInicial2 + "','YYYY-MM-DD')";
            dataFinal2 = ft.getDataFinal().replaceAll("T00:00", "");
            dataFinal2 = "to_date('"+ dataFinal2 + "','YYYY-MM-DD')";
            if(ft.getDataFinal().equals("null")) {
                dataFinal2 = "null";
            }
            insert += "INSERT INTO Ciclo_Rega (Cicloid, Setorid, data_inicial, data_final) VALUES (" + id + "," + ft.getIdSetor() + "," + dataInicial2 + "," + dataFinal2 + ");\n";
        }
        return insert;
    }
}