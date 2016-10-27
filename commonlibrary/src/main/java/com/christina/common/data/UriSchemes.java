package com.christina.common.data;

import com.christina.common.contract.Contracts;

public final class UriSchemes {
    public static final String CONTENT = "content";

    public static final String DATA = "data";

    public static final String FILE = "file";

    public static final String FTP = "ftp";

    public static final String FTPS = "ftps";

    public static final String GEO = "geo";

    public static final String HTTP = "http";

    public static final String HTTPS = "https";

    public static final String MAILTO = "mailto";

    public static final String MAGNET = "magnet";

    public static final String TEL = "tel";

    public static final String SMS = "sms";

    public static final String URN = "urn";

    private UriSchemes() {
        Contracts.unreachable();
    }
}
