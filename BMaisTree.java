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

    private No localizarNo(int info)
    {
        No no=raiz;
        boolean achou=false;
        while(no!=null && !achou)
        {
            int pos=no.procurarPosicao(info);
            if(pos<no.getTl() && no.getvInfo(pos)==info)
                achou=true;
            else
                no = no.getvLig(pos);
        }
        return no;
    }

}
