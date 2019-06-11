package onet.grupa.domaincontroller.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.Expose;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "domain_name",
        "contact_handle",
        "contact_tech_status",
        "domain_promotion",
        "domain_registration_date",
        "domain_expire_date",
        "domain_renew_until_date",
        "domain_status",
        "domain_nameservers",
        "domain_type",
        "domain_block",
        "domain_active_configuration",
        "domain_configuration_details",
        "domain_dnssec_supported_mode",
        "domain_dnssec_supported_algorithms",
        "domain_dnssec_supported_digest_types",
        "domain_dnssec_active",
        "domain_dnssec_data",
        "domain_child_hosts",
        "domain_autorenew"
})
public class Result {
    @Expose
    @JsonProperty("domain_name")
    private String domain_name;

    @Expose
    @JsonProperty("contact_handle")
    private String contact_handle;

    @Expose
    @JsonProperty("contact_tech_status")
    private String contact_tech_status;

    @Expose
    @JsonProperty("domain_promotion")
    private String domain_promotion;

    @Expose
    @JsonProperty("domain_registration_date")
    private String domain_registration_date;

    @Expose
    @JsonProperty("domain_expire_date")
    private String domain_expire_date;

    @Expose
    @JsonProperty("domain_renew_until_date")
    private String domain_renew_until_date;

    @Expose
    @JsonProperty("domain_status")
    private String domain_status;

    @Expose
    @JsonProperty("domain_nameservers")
    private List<String> domain_nameservers = null;

    @Expose
    @JsonProperty("domain_type")
    private String domain_type;

    @Expose
    @JsonProperty("domain_block")
    private Boolean domain_block;

    @Expose
    @JsonProperty("domain_active_configuration")
    private String domain_active_configuration;

    @JsonProperty("domain_configuration_details")
    @JsonIgnore
    @Expose(serialize = false, deserialize = false)
    private Domain_configuration_details[] domain_configuration_details = null;

    @Expose
    @JsonProperty("domain_dnssec_supported_mode")
    private Integer domain_dnssec_supported_mode;

    @Expose
    @JsonProperty("domain_dnssec_supported_algorithms")
    private List<Integer> domain_dnssec_supported_algorithms = null;

    @Expose
    @JsonProperty("domain_dnssec_supported_digest_types")
    private List<Integer> domain_dnssec_supported_digest_types = null;

    @Expose
    @JsonProperty("domain_dnssec_active")
    private Boolean domain_dnssec_active;

    @JsonProperty("domain_dnssec_data")
    @JsonIgnore
    @Expose(serialize = false, deserialize = false)
    private List<String> domain_dnssec_data = null;

    @JsonProperty("domain_child_hosts")
    @JsonIgnore
    @Expose(serialize = false, deserialize = false)
    private List<String> domain_child_hosts = null;

    @Expose
    @JsonProperty("domain_autorenew")
    private Boolean domain_autorenew;

    public List<String> getDomain_dnssec_data() {
        return domain_dnssec_data;
    }

    public void setDomain_dnssec_data(List<String> domain_dnssec_data) {
        this.domain_dnssec_data = domain_dnssec_data;
    }

    @JsonProperty("domain_name")
    public String getDomain_name() {
        return domain_name;
    }

    @JsonProperty("domain_name")
    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }

    @JsonProperty("domain_type")
    public String getDomain_type() {
        return domain_type;
    }

    @JsonProperty("domain_type")
    public void setDomain_type(String domain_type) {
        this.domain_type = domain_type;
    }

    @JsonProperty("contact_handle")
    public String getContact_handle() {
        return contact_handle;
    }

    @JsonProperty("contact_handle")
    public void setContact_handle(String contact_handle) {
        this.contact_handle = contact_handle;
    }

    @JsonProperty("contact_tech_status")
    public String getContact_tech_status() {
        return contact_tech_status;
    }

    @JsonProperty("contact_tech_status")
    public void setContact_tech_status(String contact_tech_status) {
        this.contact_tech_status = contact_tech_status;
    }

    @JsonProperty("domain_promotion")
    public String getDomain_promotion() {
        return domain_promotion;
    }

    @JsonProperty("domain_promotion")
    public void setDomain_promotion(String domain_promotion) {
        this.domain_promotion = domain_promotion;
    }

    @JsonProperty("domain_registration_date")
    public String getDomain_registration_date() {
        return domain_registration_date;
    }

    @JsonProperty("domain_registration_date")
    public void setDomain_registration_date(String domain_registration_date) {
        this.domain_registration_date = domain_registration_date;
    }

    @JsonProperty("domain_expire_date")
    public String getDomain_expire_date() {
        return domain_expire_date;
    }

    @JsonProperty("domain_expire_date")
    public void setDomain_expire_date(String domain_expire_date) {
        this.domain_expire_date = domain_expire_date;
    }

    @JsonProperty("domain_renew_until_date")
    public String getDomain_renew_until_date() {
        return domain_renew_until_date;
    }

    @JsonProperty("domain_renew_until_date")
    public void setDomain_renew_until_date(String domain_renew_until_date) {
        this.domain_renew_until_date = domain_renew_until_date;
    }

    @JsonProperty("domain_status")
    public String getDomain_status() {
        return domain_status;
    }

    @JsonProperty("domain_status")
    public void setDomain_status(String domain_status) {
        this.domain_status = domain_status;
    }

    @JsonProperty("domain_nameservers")
    public List<String> getDomain_nameservers() {
        return domain_nameservers;
    }

    @JsonProperty("domain_nameservers")
    public void setDomain_nameservers(List<String> domain_nameservers) {
        this.domain_nameservers = domain_nameservers;
    }

    @JsonProperty("domain_block")
    public Boolean getDomain_block() {
        return domain_block;
    }

    @JsonProperty("domain_block")
    public void setDomain_block(Boolean domain_block) {
        this.domain_block = domain_block;
    }

    @JsonProperty("domain_active_configuration")
    public String getDomain_active_configuration() {
        return domain_active_configuration;
    }

    @JsonProperty("domain_active_configuration")
    public void setDomain_active_configuration(String domain_active_configuration) {
        this.domain_active_configuration = domain_active_configuration;
    }

//    @JsonProperty("domain_configuration_details")
//    public Domain_configuration_details[] getDomain_configuration_details() {
//        return domain_configuration_details[];
//    }
//
//    @JsonProperty("domain_configuration_details")
//    public void setDomain_configuration_details(Domain_configuration_details[] domain_configuration_details) {
//        this.domain_configuration_details[] = domain_configuration_details[];
//    }

    @JsonProperty("domain_dnssec_supported_mode")
    public Integer getDomain_dnssec_supported_mode() {
        return domain_dnssec_supported_mode;
    }

    @JsonProperty("domain_dnssec_supported_mode")
    public void setDomain_dnssec_supported_mode(Integer domain_dnssec_supported_mode) {
        this.domain_dnssec_supported_mode = domain_dnssec_supported_mode;
    }

    @JsonProperty("domain_dnssec_supported_algorithms")
    public List<Integer> getDomain_dnssec_supported_algorithms() {
        return domain_dnssec_supported_algorithms;
    }

    @JsonProperty("domain_dnssec_supported_algorithms")
    public void setDomain_dnssec_supported_algorithms(List<Integer> domain_dnssec_supported_algorithms) {
        this.domain_dnssec_supported_algorithms = domain_dnssec_supported_algorithms;
    }

    @JsonProperty("domain_dnssec_supported_digest_types")
    public List<Integer> getDomain_dnssec_supported_digest_types() {
        return domain_dnssec_supported_digest_types;
    }

    @JsonProperty("domain_dnssec_supported_digest_types")
    public void setDomain_dnssec_supported_digest_types(List<Integer> domain_dnssec_supported_digest_types) {
        this.domain_dnssec_supported_digest_types = domain_dnssec_supported_digest_types;
    }

    @JsonProperty("domain_dnssec_active")
    public Boolean getDomain_dnssec_active() {
        return domain_dnssec_active;
    }

    @JsonProperty("domain_dnssec_active")
    public void setDomain_dnssec_active(Boolean domain_dnssec_active) {
        this.domain_dnssec_active = domain_dnssec_active;
    }

    @JsonProperty("domain_autorenew")
    public Boolean getDomain_autorenew() {
        return domain_autorenew;
    }

    @JsonProperty("domain_autorenew")
    public void setDomain_autorenew(Boolean domain_autorenew) {
        this.domain_autorenew = domain_autorenew;
    }

}