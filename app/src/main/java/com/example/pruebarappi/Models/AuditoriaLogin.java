package com.example.pruebarappi.Models;

import android.provider.BaseColumns;

public class AuditoriaLogin {

    /**
     * @Autor: Luis Jimenez
     * @Creado el: 13/01/2017
     * @Descripcion Modelo para cargar los accesos a la aplicacion
     * @Version 1.0
     */
    public final class auditoriaLoginEntity implements BaseColumns {
        public static final String TABLE_NAME = "TBL_AUDITORIA_LOGIN";
        public static final String COLUMN_NAME_USER_ID = "userId";
        public static final String COLUMN_NAME_DATE_LOGIN = "dateLogin";
    }

}
