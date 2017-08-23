package idv.evan.tools.client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.beust.jcommander.internal.Maps;
import com.google.common.base.Strings;
import com.maxmind.geoip2.WebServiceClient;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

public class ClientHelper {

	private static WebServiceClient S_CLIENT;
	private static int geoip2UserId = 4;
	private static String geoip2LicenseKey = "";
	private static boolean IS_BUILD = false;
	private static ClientHelper CLIENT_HELPER = null;

	private ClientHelper() {
	}

	public static ClientHelper build() {
		if (CLIENT_HELPER == null) {
			CLIENT_HELPER = new ClientHelper();
			try {
				buildClient();
				IS_BUILD = true;
			} catch (IOException | GeoIp2Exception e) {
				System.err.printf("ERROR: %s", e.getMessage());
			}
		}
		return CLIENT_HELPER;
	}

	private static void buildClient() throws IOException, GeoIp2Exception {
		S_CLIENT = new WebServiceClient.Builder(geoip2UserId, geoip2LicenseKey).build();
	}

	private static CityResponse getCityResp(String clientIp) throws IOException {
		CityResponse response = null;
		if (!IS_BUILD) {
			throw new IOException("is Not Build yet !");
		}
		try {
			response = S_CLIENT.city(InetAddress.getByName(clientIp));
		} catch (GeoIp2Exception e) {
			System.err.printf("ERROR: %s", e.getMessage());
		}
		return response;
	}

	public Country getCountry(String clientIp) throws IOException {
		return getCityResp(clientIp).getCountry();
	}

	public Subdivision getSubdivision(String clientIp) throws IOException {
		return getCityResp(clientIp).getMostSpecificSubdivision();
	}

	public List<Subdivision> getSubdivisions(String clientIp) throws IOException {
		return getCityResp(clientIp).getSubdivisions();
	}

	public City getCity(String clientIp) throws IOException {
		return getCityResp(clientIp).getCity();
	}

	public Postal getPostal(String clientIp) throws IOException {
		return getCityResp(clientIp).getPostal();
	}

	public Location getLocation(String clientIp) throws IOException {
		return getCityResp(clientIp).getLocation();
	}
	
	public Country getCountryByRequest(HttpServletRequest req) throws IOException {
		return getCityResp(getClientIP(req)).getCountry();
	}

	public Subdivision getSubdivisionByRequest(HttpServletRequest req) throws IOException {
		return getCityResp(getClientIP(req)).getMostSpecificSubdivision();
	}

	public List<Subdivision> getSubdivisionsByRequest(HttpServletRequest req) throws IOException {
		return getCityResp(getClientIP(req)).getSubdivisions();
	}

	public City getCityByRequest(HttpServletRequest req) throws IOException {
		return getCityResp(getClientIP(req)).getCity();
	}

	public Postal getPostalByRequest(HttpServletRequest req) throws IOException {
		return getCityResp(getClientIP(req)).getPostal();
	}

	public Location getLocationByRequest(HttpServletRequest req) throws IOException {
		return getCityResp(getClientIP(req)).getLocation();
	}

	public String getClientIP(HttpServletRequest request) {
		Map<String, String> resp = getRequestHeadersInMap(request);
		return Strings.nullToEmpty(resp.get("X-FORWARDED-FOR"));
	}

	private Map<String, String> getRequestHeadersInMap(HttpServletRequest request) {
		Map<String, String> result = Maps.newHashMap();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			result.put(key.toUpperCase(), request.getHeader(key));
		}
		return result;
	}

}
