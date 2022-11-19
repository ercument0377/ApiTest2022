package class06_pojos;

public class GoRestDataPojo {
    private Object meta;
    private Data data;

    public GoRestPojo() {
    }

    public GoRestPojo(Object meta, Data data) {
        super();
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoRestDataPojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}

}
