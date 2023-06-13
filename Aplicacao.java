public class Aplicacao {
    
    public static void main(String[] args) {
        
        BMaisTree bMais = new BMaisTree();
        bMais.inserir(1); 
        bMais.inserir(4); 
        bMais.inserir(7); 
        bMais.inserir(10); 
        bMais.inserir(17); 
        bMais.inserir(21); 
        bMais.inserir(31); 
        bMais.inserir(25); 
        bMais.inserir(19);
        bMais.inserir(20);
        bMais.inserir(28);
        bMais.inserir(42); 
        bMais.ExibeNormal();
        bMais.ExibeFolhasFormatada();
        System.out.println("\n----------------IN_ORDEM---------");
        bMais.in_ordem();
        
    }


}
