package persistence;

import model.Ad;
import java.util.List;

public interface Store {
    List<Ad> findRecent();
    List<Ad> findWithPhoto();
    List<Ad> findByBrand(String brandName);
}
