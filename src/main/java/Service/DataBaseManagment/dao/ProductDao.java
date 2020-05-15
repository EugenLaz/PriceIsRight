package Service.DataBaseManagment.dao;

import configuration.HibernateUtil;
import entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Transactional
public class ProductDao {
    Logger logger = LogManager.getLogger(ProductDao.class);

    public void saveProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            logger.info("Product has been successfully saved to dataBase");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public Product getRandomProduct() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Product> products = session.createQuery("FROM Product", Product.class).list();
            Product product = products.get(ThreadLocalRandom.current().nextInt(0, products.size()));
            logger.info(product.getName().substring(0, 10) + " has been retrieved from database");
            return product;
        }
    }

    public void deleteAllProducts() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM Product");
            transaction.commit();
            logger.info("All products were deleted from database");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.warn("Exception occurred during deletion of all DataBase rows");
            logger.error(e.getStackTrace());
        }
    }

}
