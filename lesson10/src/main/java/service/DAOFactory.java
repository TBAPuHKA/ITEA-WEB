package service;



public abstract class DAOFactory {
	
	public final static int MY_SQL = 1;

	public static DAOFactory getInstance(int sourceType) {
		switch (sourceType) {
		case MY_SQL:
			return new MySqlDAOFactory();
		default:
			return null;
		}
	}

	public abstract UserDAO getUserDAO();
	public abstract ProductDAO getProductDAO();

}
