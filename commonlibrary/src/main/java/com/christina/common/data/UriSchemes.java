package com.christina.common.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.christina.common.contract.Contracts;

import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(prefix = "_")
public enum UriSchemes {
    AAA("aaa"),
    AAAS("aaas"),
    ABOUT("about"),
    ACAP("acap"),
    ACCT("acct"),
    ACR("acr", "prov/acr"),
    ADIUMXTRA("adiumxtra", "prov/adiumxtra"),
    AFP("afp", "prov/afp"),
    AFS("afs"),
    AIM("aim", "prov/aim"),
    APPDATA("appdata", "prov/appdata"),
    APT("apt", "prov/apt"),
    ATTACHMENT("attachment", "prov/attachment"),
    AW("aw", "prov/aw"),
    BARION("barion", "prov/barion"),
    BESHARE("beshare", "prov/beshare"),
    BITCOIN("bitcoin", "prov/bitcoin"),
    BLOB("blob", "prov/blob"),
    BOLO("bolo", "prov/bolo"),
    BROWSEREXT("browserext", "prov/browserext"),
    CALLTO("callto", "prov/callto"),
    CAP("cap"),
    CHROME("chrome", "prov/chrome"),
    CHROME_EXTENSION("chrome-extension", "prov/chrome-extension"),
    CID("cid"),
    COAP("coap"),
    COAPS("coaps"),
    COM_EVENTBRITE_ATTENDEE("com-eventbrite-attendee", "prov/com-eventbrite-attendee"),
    CONTENT("content", "prov/content"),
    CRID("crid"),
    CVS("cvs", "prov/cvs"),
    DATA("data"),
    DAV("dav"),
    DICT("dict"),
    DIS("dis", "prov/dis"),
    DLNA_PLAYCONTAINER("dlna-playcontainer", "prov/dlna-playcontainer"),
    DLNA_PLAYSINGLE("dlna-playsingle", "prov/dlna-playsingle"),
    DNS("dns"),
    DNTP("dntp", "prov/dntp"),
    DTN("dtn"),
    DVB("dvb"),
    ED2K("ed2k", "prov/ed2k"),
    EXAMPLE("example"),
    FACETIME("facetime", "prov/facetime"),
    FAX("fax"),
    FEED("feed", "prov/feed"),
    FEEDREADY("feedready", "prov/feedready"),
    FILE("file"),
    FILESYSTEM("filesystem", "historic/filesystem"),
    FINGER("finger", "prov/finger"),
    FISH("fish", "prov/fish"),
    FTP("ftp"),
    GEO("geo"),
    GG("gg", "prov/gg"),
    GIT("git", "prov/git"),
    GIZMOPROJECT("gizmoproject", "prov/gizmoproject"),
    GO("go"),
    GOPHER("gopher"),
    GTALK("gtalk", "prov/gtalk"),
    H323("h323"),
    HAM("ham"),
    HCP("hcp", "prov/hcp"),
    HTTP("http"),
    HTTPS("https"),
    IAX("iax"),
    ICAP("icap"),
    ICON("icon"),
    IM("im"),
    IMAP("imap"),
    INFO("info"),
    IOTDISCO("iotdisco", "prov/iotdisco"),
    IPN("ipn"),
    IPP("ipp"),
    IPPS("ipps"),
    IRC("irc", "prov/irc"),
    IRC6("irc6", "prov/irc6"),
    IRCS("ircs", "prov/ircs"),
    IRIS("iris"),
    IRIS_BEEP("iris.beep"),
    IRIS_LWZ("iris.lwz"),
    IRIS_XPC("iris.xpc"),
    IRIS_XPCS("iris.xpcs"),
    ISOSTORE("isostore", "prov/isostore"),
    ITMS("itms", "prov/itms"),
    JABBER("jabber", "perm/jabber"),
    JAR("jar", "prov/jar"),
    JMS("jms"),
    KEYPARC("keyparc", "prov/keyparc"),
    LASTFM("lastfm", "prov/lastfm"),
    LDAP("ldap"),
    LDAPS("ldaps", "prov/ldaps"),
    LVLT("lvlt", "prov/lvlt"),
    MAGNET("magnet", "prov/magnet"),
    MAILSERVER("mailserver"),
    MAILTO("mailto"),
    MAPS("maps", "prov/maps"),
    MARKET("market", "prov/market"),
    MESSAGE("message", "prov/message"),
    MID("mid"),
    MMS("mms", "prov/mms"),
    MODEM("modem"),
    MS_ACCESS("ms-access", "prov/ms-access"),
    MS_BROWSER_EXTENSION("ms-browser-extension", "prov/ms-browser-extension"),
    MS_DRIVE_TO("ms-drive-to", "prov/ms-drive-to"),
    MS_ENROLLMENT("ms-enrollment", "prov/ms-enrollment"),
    MS_EXCEL("ms-excel", "prov/ms-excel"),
    MS_GAMEBARSERVICES("ms-gamebarservices", "prov/ms-gamebarservices"),
    MS_GETOFFICE("ms-getoffice", "prov/ms-getoffice"),
    MS_HELP("ms-help", "prov/ms-help"),
    MS_INFOPATH("ms-infopath", "prov/ms-infopath"),
    MS_MEDIA_STREAM_ID("ms-media-stream-id", "prov/ms-media-stream-id"),
    MS_PROJECT("ms-project", "prov/ms-project"),
    MS_POWERPOINT("ms-powerpoint", "prov/ms-powerpoint"),
    MS_PUBLISHER("ms-publisher", "prov/ms-publisher"),
    MS_SEARCH_REPAIR("ms-search-repair", "prov/ms-search-repair"),
    MS_SECONDARY_SCREEN_CONTROLLER("ms-secondary-screen-controller",
        "prov/ms-secondary-screen-controller"),
    MS_SECONDARY_SCREEN_SETUP("ms-secondary-screen-setup", "prov/ms-secondary-screen-setup"),
    MS_SETTINGS("ms-settings", "prov/ms-settings"),
    MS_SETTINGS_AIRPLANEMODE("ms-settings-airplanemode", "prov/ms-settings-airplanemode"),
    MS_SETTINGS_BLUETOOTH("ms-settings-bluetooth", "prov/ms-settings-bluetooth"),
    MS_SETTINGS_CAMERA("ms-settings-camera", "prov/ms-settings-camera"),
    MS_SETTINGS_CELLULAR("ms-settings-cellular", "prov/ms-settings-cellular"),
    MS_SETTINGS_CLOUDSTORAGE("ms-settings-cloudstorage", "prov/ms-settings-cloudstorage"),
    MS_SETTINGS_CONNECTABLEDEVICES("ms-settings-connectabledevices",
        "prov/ms-settings-connectabledevices"),
    MS_SETTINGS_DISPLAYS_TOPOLOGY("ms-settings-displays-topology",
        "prov/ms-settings-displays-topology"),
    MS_SETTINGS_EMAILANDACCOUNTS("ms-settings-emailandaccounts",
        "prov/ms-settings-emailandaccounts"),
    MS_SETTINGS_LANGUAGE("ms-settings-language", "prov/ms-settings-language"),
    MS_SETTINGS_LOCATION("ms-settings-location", "prov/ms-settings-location"),
    MS_SETTINGS_LOCK("ms-settings-lock", "prov/ms-settings-lock"),
    MS_SETTINGS_NFCTRANSACTIONS("ms-settings-nfctransactions", "prov/ms-settings-nfctransactions"),
    MS_SETTINGS_NOTIFICATIONS("ms-settings-notifications", "prov/ms-settings-notifications"),
    MS_SETTINGS_POWER("ms-settings-power", "prov/ms-settings-power"),
    MS_SETTINGS_PRIVACY("ms-settings-privacy", "prov/ms-settings-privacy"),
    MS_SETTINGS_PROXIMITY("ms-settings-proximity", "prov/ms-settings-proximity"),
    MS_SETTINGS_SCREENROTATION("ms-settings-screenrotation", "prov/ms-settings-screenrotation"),
    MS_SETTINGS_WIFI("ms-settings-wifi", "prov/ms-settings-wifi"),
    MS_SETTINGS_WORKPLACE("ms-settings-workplace", "prov/ms-settings-workplace"),
    MS_SPD("ms-spd", "prov/ms-spd"),
    MS_TRANSIT_TO("ms-transit-to", "prov/ms-transit-to"),
    MS_VIRTUALTOUCHPAD("ms-virtualtouchpad", "prov/ms-virtualtouchpad"),
    MS_VISIO("ms-visio", "prov/ms-visio"),
    MS_WALK_TO("ms-walk-to", "prov/ms-walk-to"),
    MS_WORD("ms-word", "prov/ms-word"),
    MSNIM("msnim", "prov/msnim"),
    MSRP("msrp"),
    MSRPS("msrps"),
    MTQP("mtqp"),
    MUMBLE("mumble", "prov/mumble"),
    MUPDATE("mupdate"),
    MVN("mvn", "prov/mvn"),
    NEWS("news"),
    NFS("nfs"),
    NI("ni"),
    NIH("nih"),
    NNTP("nntp"),
    NOTES("notes", "prov/notes"),
    OCF("ocf", "prov/ocf"),
    OID("oid", "prov/oid"),
    OPAQUELOCKTOKEN("opaquelocktoken"),
    PACK("pack", "historic/pack"),
    PALM("palm", "prov/palm"),
    PAPARAZZI("paparazzi", "prov/paparazzi"),
    PKCS11("pkcs11"),
    PLATFORM("platform", "prov/platform"),
    POP("pop"),
    PRES("pres"),
    PROSPERO("prospero"),
    PROXY("proxy", "prov/proxy"),
    PSYC("psyc", "prov/psyc"),
    QB("qb", "prov/qb"),
    QUERY("query", "prov/query"),
    REDIS("redis", "prov/redis"),
    REDISS("rediss", "prov/rediss"),
    RELOAD("reload"),
    RES("res", "prov/res"),
    RESOURCE("resource", "prov/resource"),
    RMI("rmi", "prov/rmi"),
    RSYNC("rsync"),
    RTMFP("rtmfp", "prov/rtmfp"),
    RTMP("rtmp", "prov/rtmp"),
    RTSP("rtsp"),
    RTSPS("rtsps"),
    RTSPU("rtspu"),
    SECONDLIFE("secondlife", "prov/secondlife"),
    SERVICE("service"),
    SESSION("session"),
    SFTP("sftp", "prov/sftp"),
    SGN("sgn", "prov/sgn"),
    SHTTP("shttp"),
    SIEVE("sieve"),
    SIP("sip"),
    SIPS("sips"),
    SKYPE("skype", "prov/skype"),
    SMB("smb", "prov/smb"),
    SMS("sms"),
    SMTP("smtp", "prov/smtp"),
    SNEWS("snews"),
    SNMP("snmp"),
    SOAP_BEEP("soap.beep"),
    SOAP_BEEPS("soap.beeps"),
    SOLDAT("soldat", "prov/soldat"),
    SPOTIFY("spotify", "prov/spotify"),
    SSH("ssh", "prov/ssh"),
    STEAM("steam", "prov/steam"),
    STUN("stun"),
    STUNS("stuns"),
    SUBMIT("submit", "prov/submit"),
    SVN("svn", "prov/svn"),
    TAG("tag"),
    TEAMSPEAK("teamspeak", "prov/teamspeak"),
    TEL("tel"),
    TELIAEID("teliaeid", "prov/teliaeid"),
    TELNET("telnet"),
    TFTP("tftp"),
    THINGS("things", "prov/things"),
    THISMESSAGE("thismessage", "perm/thismessage"),
    TIP("tip"),
    TN3270("tn3270"),
    TOOL("tool", "prov/tool"),
    TURN("turn"),
    TURNS("turns"),
    TV("tv"),
    UDP("udp", "prov/udp"),
    UNREAL("unreal", "prov/unreal"),
    URN("urn"),
    UT2004("ut2004", "prov/ut2004"),
    V_EVENT("v-event", "prov/v-event"),
    VEMMI("vemmi"),
    VENTRILO("ventrilo", "prov/ventrilo"),
    VIDEOTEX("videotex", "historic/videotex"),
    VNC("vnc"),
    VIEW_SOURCE("view-source", "prov/view-source"),
    WAIS("wais"),
    WEBCAL("webcal", "prov/webcal"),
    WPID("wpid", "prov/wpid"),
    WS("ws"),
    WSS("wss"),
    WTAI("wtai", "prov/wtai"),
    WYCIWYG("wyciwyg", "prov/wyciwyg"),
    XCON("xcon"),
    XCON_USERID("xcon-userid"),
    XFIRE("xfire", "prov/xfire"),
    XMLRPC_BEEP("xmlrpc.beep"),
    XMLRPC_BEEPS("xmlrpc.beeps"),
    XMPP("xmpp"),
    XRI("xri", "prov/xri"),
    YMSGR("ymsgr", "prov/ymsgr"),
    Z39_50("z39.50"),
    Z39_50R("z39.50r"),
    Z39_50S("z39.50s");

    @Getter
    @NonNull
    private final String _schemeName;

    @Getter
    @Nullable
    private final String _template;

    UriSchemes(@NonNull final String schemeName) {
        this(schemeName, null);
    }

    UriSchemes(@NonNull final String schemeName, @Nullable final String template) {
        Contracts.requireNonNull(schemeName, "schemeName == null");

        _schemeName = schemeName;
        _template = template;
    }
}
