import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lesson2ReentrantReadWrite {

    /*

        1. ReentrantReadWriteLock
         - In a race condition requires multiple threads sharing a resource and at least one modifying
           Solution -> Complete exclusion regardless operation (read/write)

         - Synchronized and ReentrantLock do not allow multiple readers to access a resource concurrently

         ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
         Allows us to share a resource by multiple threads at the same time reading and complete exclusion on write

         rwLock.readLock().tryLock()
         rwLock.writeLock().tryLock()

         rwLock.writeLock().unlock()
         rwLock.writeLock().unlock()

         Not always ReentrantReadWriteLock it's better than a conventional lock -> Use the right tool for the job
    */

    //Example

    private final HashMap<Integer, List<String>> productIdToReviews;

    // Create your member variables here

     private static final ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    Lock getLockForAddProduct() {
        return reentrantReadWriteLock.writeLock();
    }

    Lock getLockForRemoveProduct() {
        return reentrantReadWriteLock.writeLock();
    }

    Lock getLockForAddProductReview() {
        return reentrantReadWriteLock.writeLock();
    }

    Lock getLockForGetAllProductReviews() {
        return reentrantReadWriteLock.readLock();
    }

    Lock getLockForGetLatestReview() {
        return reentrantReadWriteLock.readLock();
    }

    Lock getLockForGetAllProductIdsWithReviews() {
        return reentrantReadWriteLock.readLock();
    }


    /********* DO NOT MODIFY THIS SECTION **************/

    public Lesson2ReentrantReadWrite() {
        this.productIdToReviews = new HashMap<>();
    }

    /**
     * Adds a product ID if not present
     */
    public void addProduct(int productId) {
        Lock lock = getLockForAddProduct();

        lock.lock();

        try {
            if (!productIdToReviews.containsKey(productId)) {
                productIdToReviews.put(productId, new ArrayList<>());
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Removes a product by ID if present
     */
    public void removeProduct(int productId) {
        Lock lock = getLockForRemoveProduct();

        lock.lock();

        try {
            if (productIdToReviews.containsKey(productId)) {
                productIdToReviews.remove(productId);
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Adds a new review to a product
     * @param productId - existing or new product ID
     * @param review - text containing the product review
     */
    public void addProductReview(int productId, String review) {
        Lock lock = getLockForAddProductReview();

        lock.lock();

        try {
            if (!productIdToReviews.containsKey(productId)) {
                productIdToReviews.put(productId, new ArrayList<>());
            }
            productIdToReviews.get(productId).add(review);
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns all the reviews for a given product
     */
    public List<String> getAllProductReviews(int productId) {
        Lock lock = getLockForGetAllProductReviews();

        lock.lock();

        try {
            if (productIdToReviews.containsKey(productId)) {
                return Collections.unmodifiableList(productIdToReviews.get(productId));
            }
        } finally {
            lock.unlock();
        }

        return Collections.emptyList();
    }

    /**
     * Returns the latest review for a product by product ID
     */
    public Optional<String> getLatestReview(int productId) {
        Lock lock = getLockForGetLatestReview();

        lock.lock();

        try {

            if (productIdToReviews.containsKey(productId) && !productIdToReviews.get(productId).isEmpty()) {
                List<String> reviews = productIdToReviews.get(productId);
                return Optional.of(reviews.get(reviews.size() - 1));
            }
        } finally {
            lock.unlock();
        }

        return Optional.empty();
    }

    /**
     * Returns all the product IDs that contain reviews
     */
    public Set<Integer> getAllProductIdsWithReviews() {
        Lock lock = getLockForGetAllProductIdsWithReviews();

        lock.lock();

        try {
            Set<Integer> productsWithReviews = new HashSet<>();
            for (Map.Entry<Integer, List<String>> productEntry : productIdToReviews.entrySet()) {
                if (!productEntry.getValue().isEmpty()) {
                    productsWithReviews.add(productEntry.getKey());
                }
            }
            return productsWithReviews;
        } finally {
            lock.unlock();
        }
    }

    /********* END OF UNMODIFIABLE SECTION **************/
}

