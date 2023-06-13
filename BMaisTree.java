public class BMaisTree implements Constantes {
    
    No raiz;

    BMaisTree()
    {
        raiz = null;
    }

    private No navegarAteFolha(int info)
    {
        int pos;
        No no=raiz;
        while(no.getvLig(0) != null)
        {
            pos = no.procurarPosicao(info);
            no = no.getvLig(pos);
        }
        return no;
    }

    private No localizarPai(No folha, int info)
    {
        int pos;
        No no=raiz, pai=raiz;
        while(no!=folha)
        {
            pai=no;
            pos = no.procurarPosicao(info);
            no = no.getvLig(pos);
        }
        return pai;
    }

    public void split(No folha, No pai)
    {
        No cx1, cx2, irmaE = null, irmaD = null;
        int iqtde, pospai, pos;
        double qtde;
        cx1 = new No();
        cx2 = new No(); 
        qtde = m-1;
        qtde = qtde / 2;
        iqtde = (int)Math.round(qtde); 
        if(folha.getvLig(0) == null)
        { 
            for(int i = 0; i < iqtde; i++)
            {
                cx1.setvInfo(cx1.getTl(), folha.getvInfo(i));                
                cx1.setTl(cx1.getTl() + 1);           
            }
            for(int i = iqtde; i < folha.getTl(); i++)
            { 
                cx2.setvInfo(cx2.getTl(), folha.getvInfo(i));               
                cx2.setTl(cx2.getTl() + 1);           
            }
            pospai = pai.procurarPosicao(cx2.getvInfo(0)); 
            if(pospai > 0)        
                irmaE = pai.getvLig(pospai - 1);            
            if(pospai < pai.getTl())
                irmaD = pai.getvLig(pospai + 1);
            cx1.setAnt(irmaE); 
            cx1.setProx(cx2);
            cx2.setAnt(cx1);
            cx2.setProx(irmaD);
            if(irmaE != null) 
                irmaE.setProx(cx1);
            if(irmaD != null)
                irmaD.setAnt(cx2);
            if(pai != folha)
            { 
                pos = pai.procurarPosicao(cx2.getvInfo(0)); 
                pai.remanejar(pos);
                pai.setvInfo(pos, folha.getvInfo(iqtde));
                pai.setvLig(pos, cx1);
                pai.setvLig(pos + 1, cx2);
                pai.setTl(pai.getTl() + 1);
            }
            else
            { 
                pai.setvInfo(0, cx2.getvInfo(0));
                pai.setTl(1);
                pai.setvLig(0, cx1);
                pai.setvLig(1, cx2);
            }
        }
        else
        { 
            for(int i = 0; i < iqtde; i++)
            {
                cx1.setvInfo(cx1.getTl(), folha.getvInfo(i));
                cx1.setvLig(cx1.getTl(), folha.getvLig(i));
                cx1.setTl(cx1.getTl() + 1);           
            }   
            cx1.setvLig(iqtde, folha.getvLig(iqtde));
            for(int i = iqtde + 1; i < folha.getTl(); i++)
            {  
                cx2.setvInfo(cx2.getTl(), folha.getvInfo(i));
                cx2.setvLig(cx2.getTl(), folha.getvLig(i));
                cx2.setTl(cx2.getTl() + 1);           
            }
            cx2.setvLig(cx2.getTl(), folha.getvLig(folha.getTl()));           
            if(pai != folha)
            { 
                pai.setvInfo(pai.getTl(), folha.getvInfo(iqtde)); 
                pai.setvLig(pai.getTl(), cx1);
                pai.setvLig(pai.getTl() + 1, cx2);
                pai.setTl(pai.getTl() + 1);
            }          
            else
            { 
                No no = new No(folha.getvInfo(iqtde));
                no.setvLig(0, cx1);
                no.setvLig(1, cx2);
                no.setTl(1);
                raiz = no;
                pai = raiz;
            }
        }
        if(pai.getTl() == m)
        {
            folha = pai;
            pai = localizarPai(folha, folha.getvInfo(0));
            split(folha, pai);
        }
    }
    
    public void inserir(int info)
    {
        No folha, pai;
        if (raiz == null)
            raiz = new No(info);
        else
        {
            folha = navegarAteFolha(info);
            int pos = folha.procurarPosicao(info);
            folha.remanejar(pos);
            folha.setvInfo(pos, info);
            folha.setTl(folha.getTl()+1);
            if(folha.getTl() == m)
            {
                pai = localizarPai(folha, info);
                split(folha, pai);
            }
        }
    }

    //-------------------------------- Exibir --------------------------------//
    public void in_ordem()
    {
        in_ordem(raiz);
    }

    private void in_ordem(No raiz)
    {
        if(raiz!=null)
        {
            for(int i=0; i<raiz.getTl() ; i++)
            {
                in_ordem(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i));
            }
            in_ordem(raiz.getvLig(raiz.getTl()));
        }
    }

    public void ExibeNormal()
    {
        No aux = raiz;
        while(aux.getvLig(0) != null)
            aux = aux.getvLig(0);
        
        System.out.print("\nExibe Folhas:[");
        while(aux.getProx() != null)
        {
            for(int i = 0; i < aux.getTl(); i++)
                System.out.print(aux.getvInfo(i) + ", ");
            aux = aux.getProx();            
        }
        for(int i = 0; i < aux.getTl() - 1; i++)
            System.out.print(aux.getvInfo(i) + ", ");  
        System.out.print(aux.getvInfo(aux.getTl() - 1) + "]");
    }

    public void ExibeFolhasFormatada()
    {
        No aux = raiz;
        while(aux.getvLig(0) != null)
            aux = aux.getvLig(0);
        
        System.out.print("\nExibe Folhas:");
        while(aux.getProx() != null)
        {
            System.out.print("[");
            for(int i = 0; i < aux.getTl(); i++)
                System.out.print(aux.getvInfo(i) + ", ");   
            aux = aux.getProx();     
            System.out.print("] => ");       
        } 
        System.out.print(" [");
        for(int i = 0; i < aux.getTl(); i++)
            System.out.print(aux.getvInfo(i) + ", ");
 
        System.out.print("]"); 
    }

}
