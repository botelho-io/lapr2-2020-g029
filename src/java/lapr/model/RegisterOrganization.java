package lapr.model;

import java.util.Set;

public class RegisterOrganization {
    private final Set<Organization> m_lstOrganizacoes;
    private final AutorizacaoFacade m_oAutorizacao;

    public Organization newOrganization(String name, Manager manager, Colaborator colaborator)
    {
        return new Organization(name, manager, colaborator);
    }

    public boolean registerOrganization(Organization organization)
    {
        if (this.validatesOrganization(organization))
        {
            Colaborador oGestor = oOrganizacao.getGestor();
            String strNomeGestor = oGestor.getNome();
            String strEmailGestor = oGestor.getEmail();
            /* pwd é criada usando um algoritmo externo que possua um adaptador que implemente AlgoritmoGeradorPasswords
               Como esses algoritmos são desconhecidos de momento, pwd será uma string "default".
            */
            String strPwd = "default";
            if (this.m_oAutorizacao.registaUtilizadorComPapeis(strNomeGestor,strEmailGestor, strPwd,
                    new String[] {Constantes.PAPEL_GESTOR_ORGANIZACAO,Constantes.PAPEL_COLABORADOR_ORGANIZACAO}))
                return addOrganizacao(oOrganizacao);
        }
        return false;
    }

    private boolean addOrganization(Organization organizacation)
    {
        return m_lstOrganizacoes.add(organizacation);
    }

    public boolean validatesOrganization(Organization organizacation)
    {
        boolean bRet = true;

        // Escrever aqui o código de validação
        if (this.m_oAutorizacao.existeUtilizador(organizacation.getManager().getEmail()))
            bRet = false;


        return bRet;
    }



    public Set<Organization> getListaOrganizacoes() {
        return m_lstOrganizacoes;
    }


}
