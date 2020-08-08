package bean;

public class ClientBean {
	
	private int idClient;
    private String nameClient;
    private String addressClient;
    private String stateClient;
    private String cityClient;
    private String cpfClient;

    public ClientBean(String nameClient, String addressClient, String stateClient, String cityClient, String cpfClient) {
        this.nameClient = nameClient;
        this.addressClient = addressClient;
        this.stateClient = stateClient;
        this.cityClient = cityClient;
        this.cpfClient = cpfClient;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getAddressClient() {
        return addressClient;
    }

    public void setAddressClient(String addressClient) {
        this.addressClient = addressClient;
    }

    public String getStateClient() {
        return stateClient;
    }

    public void setStateClient(String stateClient) {
        this.stateClient = stateClient;
    }

    public String getCityClient() {
        return cityClient;
    }

    public void setCityClient(String cityClient) {
        this.cityClient = cityClient;
    }

    public String getCpfClient() {
        return cpfClient;
    }

    public void setCpfClient(String cpfClient) {
        this.cpfClient = cpfClient;
    }
}
