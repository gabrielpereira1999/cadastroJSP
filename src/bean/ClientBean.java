package bean;

import java.util.ArrayList;

public class ClientBean {
	
    private String nameClient;
    private String addressClient;
    private String stateClient;
    private String cityClient;
    private String cpfClient;
    private ArrayList<String> telephonesClient = new ArrayList<String>();
    
    
    public ClientBean(String nameClient, String addressClient, String stateClient, String cityClient,
			String cpfClient) {
		super();
		this.nameClient = nameClient;
		this.addressClient = addressClient;
		this.stateClient = stateClient;
		this.cityClient = cityClient;
		this.cpfClient = cpfClient;
	}

	public ClientBean(String nameClient, String addressClient, String stateClient, String cityClient,
			String cpfClient, ArrayList<String> telephonesClient) {
		super();
		this.nameClient = nameClient;
		this.addressClient = addressClient;
		this.stateClient = stateClient;
		this.cityClient = cityClient;
		this.cpfClient = cpfClient;
		this.telephonesClient = telephonesClient;
	}
	
	public String toString() {
		return "Nome: " + this.nameClient + "/nCidade:" + this.cityClient + "/nEstado: " + this.stateClient + "/nEndereço: " + this.addressClient + "/nCPF: " + this.cpfClient + "/nTelefone(s): " + this.telephonesClient;
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
    

	public ArrayList<String> getTelephonesClient() {
		return telephonesClient;
	}

	public void setTelephonesClient(ArrayList<String> telephonesClient) {
		this.telephonesClient = telephonesClient;
	}

    
}
