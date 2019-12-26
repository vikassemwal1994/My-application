package mufilbito.com.videoplayer.volleygson;

import java.io.Serializable;

public class CartModel implements Serializable {

    private String ProductCode;
    private String ProductName;
    private String Image;

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
