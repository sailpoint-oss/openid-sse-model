package net.openid.sse.model;

public enum CAEPCredentialType {

    PASSWORD("password"),
    PIN("pin"),
    X509("x509"),
    FIDO2_PLATFORM("fido2-platform"),
    FIDO2_ROAMING("fido2-roaming"),
    FIDO2_U2F("fido-u2f"),
    VERIFIABLE_CREDENTIAL("verifiable-credential"),
    PHONE_VOICE("phone-voice"),
    PHONE_SMS("phone-sms"),
    APP("app");

    private final String name;

    CAEPCredentialType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
