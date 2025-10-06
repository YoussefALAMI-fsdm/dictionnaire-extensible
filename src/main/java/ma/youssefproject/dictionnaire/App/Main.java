package ma.youssefproject.dictionnaire.App ;

import ma.youssefproject.dictionnaire.view.cli.InterfaceCLI;

public class Main {           // @ Premier version livrée via docker ( version minimal )

    public static void main ( String[] args ) {

        InterfaceCLI cli = new InterfaceCLI() ;

        cli.menuDemmarage() ;
    }

}