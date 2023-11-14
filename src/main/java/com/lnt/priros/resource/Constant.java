package com.lnt.priros.resource;

import org.springframework.beans.factory.annotation.Value;

public class Constant {
    // todo. set SYSTEM_ENV
    /*public final static String SYSTEM_ENV = ConfigProperty.getProperty("system.env");*/
    public final static String SYSTEM_ENV_PROD = "PROD";
    public final static String SYSTEM_ENV_STAGE = "STAGE";
    public final static String SYSTEM_ENV_TEST = "TEST";
    public final static String SYSTEM_ENV_DEV = "DEV";
    public final static String SHA256 = "SHA-256";
    public final static String MD5 = "MD5";
    public final static String ALGORITHM_AES = "AES";
    public final static String TRANSFORMATION_PKCS5 = "AES/CBC/PKCS5Padding";
    public final static String X509 = "X.509";

    public final static String REPAY_DOCUMENT_NAME_01 = "상환영수증";
    public final static String REPAY_DOCUMENT_NAME_02 = "말소확인증";
    public final static String REPAY_DOCUMENT_NAME_03 = "기타첨부서류";
    public final static String REPAY_DOCUMENT_NAME_04 = "등기필정보보완";
    public final static String REPAY_DOCUMENT_NAME_05 = "등기부등본";


    public final static String JOIN_FIRM_FILE_NAME_01 = "사업자등록증";
    public final static String JOIN_FIRM_FILE_NAME_02 = "자격증";
    public final static String JOIN_FIRM_FILE_NAME_03 = "보험증서";

    public final static String JOIN_FIRM_STATE_WAITE = "W";
    public final static String JOIN_FIRM_STATE_ING = "I";
    public final static String JOIN_FIRM_STATE_COMPLETE = "Y";
    public final static String JOIN_FIRM_STATE_REJECT = "N";
    public final static String JOIN_FIRM_STATE_BLACK_LIST = "B";
}
