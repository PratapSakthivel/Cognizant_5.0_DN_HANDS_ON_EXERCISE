import java.util.Arrays;

class Product {
    int productId;
    String productName;
    String category;

    Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
}

public class Exercise2_EcommerceSearchFunction {
    static int linearSearch(Product[] products, String target) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].productName.equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    static int binarySearch(Product[] products, String target) {
        int left = 0, right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int cmp = products[mid].productName.compareToIgnoreCase(target);

            if (cmp == 0) return mid;
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Mobile", "Electronics"),
            new Product(3, "Shoes", "Fashion"),
            new Product(4, "Watch", "Accessories")
        };

        System.out.println("Linear Search Index: " +
                linearSearch(products, "Shoes"));

        Arrays.sort(products,
                (a, b) -> a.productName.compareToIgnoreCase(b.productName));

        System.out.println("Binary Search Index: " +
                binarySearch(products, "Shoes"));
    }
}
