package daejeon.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaejeonDAO {
	private static DaejeonDAO deptDAO = null;

	public static DaejeonDAO getInstance() {
		if (deptDAO == null) {
			deptDAO = new DaejeonDAO();
		}

		return deptDAO;

	}

	private DaejeonDAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	

	public List<DaejeonDTO> getDeptList() {

		List<DaejeonDTO> list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();

		sql.append(" select sh_name, sh_location1, sh_location2, sh_state, sh_address1, sh_address2");
		sql.append(" from Daejeon");
		sql.append(" order by sh_name asc");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "schoolsignal",
				"schoolsignal");
				PreparedStatement ps = conn.prepareStatement(sql.toString());
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				DaejeonDTO deptDTO = new DaejeonDTO();
				deptDTO.setSh_name(rs.getString("sh_name"));
				deptDTO.setSh_location1(rs.getDouble("sh_location1"));
				deptDTO.setSh_location2(rs.getDouble("sh_location2"));
				deptDTO.setSh_state(rs.getString("sh_state"));
				deptDTO.setSh_address2(rs.getString("sh_address1"));
				deptDTO.setSh_address2(rs.getString("sh_address2"));
				list.add(deptDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
