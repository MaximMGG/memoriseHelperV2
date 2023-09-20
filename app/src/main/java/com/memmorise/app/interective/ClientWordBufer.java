package com.memmorise.app.interective;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ClientWordBufer {

    public static final String SEY_HELLO_TO_USER = """
        ***********************************************************************
        ********************Greeting in MemoriseHelper*************************
        ***This project can help you with memorising any words thet you want***
        ********For start use this application, please, write you name*********
        ***********************************************************************
            """;
    
    public static final String LIST_OF_LENGUAGES = """
                    1.English
                    2.Russian
            """;
    public static final String SEY_GOODBYE_TO_USER = """
        ***********************************************************************
        ***************************Goodbye diar %s*****************************
        **************Hope you engoy Memorise Helper, see you soon*************
        ***********************************************************************
            """;
}
