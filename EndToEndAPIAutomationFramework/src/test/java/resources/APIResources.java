package resources;

public enum APIResources {



    AddPlaceApi("/maps/api/place/add/json"),
    GetPLaceApi("/maps/api/place/get/json"),
    DeletePLaceApi("/maps/api/place/delete/json");

    private String resource;
    APIResources(String resource)
    {
        this.resource=resource;
    }

    public String getResource()
    {
        return resource;

    }



}
