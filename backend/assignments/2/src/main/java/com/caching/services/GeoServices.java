package com.caching.services;

import com.caching.constants.Constants;
import com.caching.dto.ResponseDTO;
import com.caching.exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashSet;

/**
 * Processing all the API requests
 */
@Service
@CacheConfig(cacheManager = "cacheManager")
public class GeoServices {
    private static final Logger logger = LoggerFactory.getLogger(GeoServices.class);
    @Value("${api-key}")
    private String accessKey;
    private final RestTemplate restTemplate;

    @Autowired
    public GeoServices() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * To check if the given region is excluded from caching or not
     * @param region
     * @return true/false
     */
    public static boolean isExcludedRegion(String region) {
        HashSet<String> excludedRegions = new HashSet<>();
        excludedRegions.add("Goa");
        return excludedRegions.contains(region);
    }

    /**
     * Checks if the latitude and longitude are valid or not
     * @param latitudeStr
     * @param longitudeStr
     * @return true/false
     */
    private boolean isNotValid(String latitudeStr,String longitudeStr){
        try {
            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);
            return !(latitude <= 90 && latitude >= -90 && longitude <= 180 && longitude >= -180);
        } catch (NumberFormatException e) {
            return true;
        }
    }

    /**
     * Converting json to ResponseDTO
     * @param results jsonNode
     * @return ResponseDTO
     */
    public ResponseDTO jsonToResponseDTO(JsonNode results){
        double latitude = results.get("latitude").asDouble();
        double longitude = results.get("longitude").asDouble();
        String address = results.get("label").asText();
        String region = results.get("region").asText();

        return new ResponseDTO(address,latitude,longitude,region);
    }

    /**
     * Processing geocoding by given address and apply/use caching if required.
     * @param address
     * @return responseDTO which consists of latitude and longitude which we got from caching
     * along with address and region
     */
    @Cacheable(value = "geocoding", key = "#address", unless = "T(com.caching.services.GeoServices).isExcludedRegion(#result.region)")
    public ResponseDTO getLatLong(String address){
        String apiUrl = "http://api.positionstack.com/v1/forward?access_key=".concat(accessKey).concat("&query=").concat(address);
        JsonNode jsonNode = restTemplate.getForObject(apiUrl,JsonNode.class);
        if(jsonNode == null){
            logger.error(Constants.INVALID);
            throw new InvalidRequestException(Constants.INVALID);
        }

        JsonNode results = jsonNode.get("data").get(0);
        if(logger.isInfoEnabled() && results != null){
            logger.info("Result of address ".concat(address).concat(" is fetched from API!"));
            logger.info("Latitude : ".concat(results.get("latitude").asText()).concat(" Longitude : ")
                    .concat(results.get("longitude").asText()));
            return jsonToResponseDTO(results);
        }
        else{
            logger.error("Invalid address!");
            throw new InvalidRequestException("Invalid address!");
        }
    }

    /**
     * Processing reverse-geocoding by given latitude and longitude and apply/use caching if possible
     * @param latitudeStr
     * @param longitudeStr
     * @return responseDTO which consists of address and region which we got from caching
     * along with latitude and longitude
     */
    @Cacheable(value = "reverse-geocoding", key = "{#latitude,#longitude}", unless = "T(com.caching.services.GeoServices).isExcludedRegion(#result.region)")
    public ResponseDTO getAddress(String latitudeStr, String longitudeStr){
        if(isNotValid(latitudeStr,longitudeStr)){
            logger.error("Invalid latitude or longitude!");
            throw new InvalidRequestException("Invalid latitude or longitude!");
        }
        double latitude = Double.parseDouble(latitudeStr);
        double longitude = Double.parseDouble(longitudeStr);

        String apiUrl = "http://api.positionstack.com/v1/reverse?access_key=".concat(accessKey).concat("&query=")
                .concat(Double.toString(latitude)).concat(",").concat(Double.toString(longitude));
        JsonNode jsonNode = restTemplate.getForObject(apiUrl,JsonNode.class);
        if(jsonNode == null){
            logger.error(Constants.INVALID);
            throw new InvalidRequestException(Constants.INVALID);
        }

        JsonNode results = jsonNode.get("data").get(0);
        if(logger.isInfoEnabled() && results != null){
            logger.info("Result of latitude ".concat(Double.toString(latitude)).concat(" longitude ")
                    .concat(Double.toString(longitude)).concat(" is fetched from API!"));
            logger.info("Address : ".concat(results.get("label").asText()));
            return jsonToResponseDTO(results);
        }
        else{
            logger.error("Invalid latitude/longitude!");
            throw new InvalidRequestException("Invalid latitude/longitude!");
        }

    }
}
