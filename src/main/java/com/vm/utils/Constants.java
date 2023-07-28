package com.vm.utils;

public abstract class Constants {
    public static final String TYPE_NUMBER = "LONG,INTEGER,SHORT,BYTE,INT,DOUBLE,FLOAT";
    public static final String RESULT_IMPORT = "RESULT_IMPORT";

    public static class ResponseKey {
        public static final String SUCCESS = "SUCCESS";
        public static final String DELETE_SUCCESS = "DELETE_SUCCESS";
        public static final String ERROR = "ERROR";
        public static final String WARNING = "WARNING";
        public static final String RECORD_DELETED = "RECORD_DELETED";
        public static final String RECORD_INUSED = "RECORD_INUSED";
        public static final String RECORD_NOT_EXIST = "RECORD_NOT_EXIST";
        public static final String RECORD_SIGNING = "RECORD_SIGNING";
        public static final String RECORD_APPROVED = "RECORD_APPROVED";
        public static final String FILE_IS_NULL = "FILE_IS_NULL";
        public static final String FILE_INVALID_FORMAT = "FILE_INVALID_FORMAT";
        public static final String DATA_OVER = "DATA_OVER";
        public static final String NO_DATA = "NO_DATA";
    }

    public static class SequenceKey {
        public static final String EMPLOYEE = "EMPLOYEE_SEQ";
    }

    public static class AuthKey {
        public static final String HEADER_KEY = "Authorization";
        public static final String PREFIX_KEY = "Bearer";
        public static final int EXPIRATION_MS = 60000000;
    }
}
