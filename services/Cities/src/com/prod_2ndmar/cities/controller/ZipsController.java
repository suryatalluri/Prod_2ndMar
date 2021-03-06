/*Copyright (c) 2016-2017 wavemaker.com All Rights Reserved.
 This software is the confidential and proprietary information of wavemaker.com You shall not disclose such Confidential Information and shall use it only in accordance
 with the terms of the source code license agreement you entered into with wavemaker.com*/
package com.prod_2ndmar.cities.controller;

/*This is a Studio Managed File. DO NOT EDIT THIS FILE. Your changes may be reverted by Studio.*/



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wavemaker.runtime.data.exception.EntityNotFoundException;
import com.wavemaker.runtime.data.export.ExportType;
import com.wavemaker.runtime.data.expression.QueryFilter;
import com.wavemaker.runtime.file.model.Downloadable;
import com.wavemaker.tools.api.core.annotations.WMAccessVisibility;
import com.wavemaker.tools.api.core.models.AccessSpecifier;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.prod_2ndmar.cities.Zips;
import com.prod_2ndmar.cities.ZipsId;
import com.prod_2ndmar.cities.service.ZipsService;


/**
 * Controller object for domain model class Zips.
 * @see Zips
 */
@RestController("Cities.ZipsController")
@Api(value = "ZipsController", description = "Exposes APIs to work with Zips resource.")
@RequestMapping("/Cities/Zips")
public class ZipsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipsController.class);

    @Autowired
	@Qualifier("Cities.ZipsService")
	private ZipsService zipsService;

	@ApiOperation(value = "Creates a new Zips instance.")
	@RequestMapping(method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Zips createZips(@RequestBody Zips zips) {
		LOGGER.debug("Create Zips with information: {}" , zips);

		zips = zipsService.create(zips);
		LOGGER.debug("Created Zips with information: {}" , zips);

	    return zips;
	}

    @ApiOperation(value = "Returns the Zips instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Zips getZips(@RequestParam("zip") Integer zip,@RequestParam("state") String state,@RequestParam("city") String city,@RequestParam("lat") String lat,@RequestParam("lng") String lng) throws EntityNotFoundException {

        ZipsId zipsId = new ZipsId();
        zipsId.setZip(zip);
        zipsId.setState(state);
        zipsId.setCity(city);
        zipsId.setLat(lat);
        zipsId.setLng(lng);

        LOGGER.debug("Getting Zips with id: {}" , zipsId);
        Zips zips = zipsService.getById(zipsId);
        LOGGER.debug("Zips details with id: {}" , zips);

        return zips;
    }



    @ApiOperation(value = "Updates the Zips instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.PUT)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Zips editZips(@RequestParam("zip") Integer zip,@RequestParam("state") String state,@RequestParam("city") String city,@RequestParam("lat") String lat,@RequestParam("lng") String lng, @RequestBody Zips zips) throws EntityNotFoundException {

        zips.setZip(zip);
        zips.setState(state);
        zips.setCity(city);
        zips.setLat(lat);
        zips.setLng(lng);

        LOGGER.debug("Zips details with id is updated with: {}" , zips);

        return zipsService.update(zips);
    }


    @ApiOperation(value = "Deletes the Zips instance associated with the given composite-id.")
    @RequestMapping(value = "/composite-id", method = RequestMethod.DELETE)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public boolean deleteZips(@RequestParam("zip") Integer zip,@RequestParam("state") String state,@RequestParam("city") String city,@RequestParam("lat") String lat,@RequestParam("lng") String lng) throws EntityNotFoundException {

        ZipsId zipsId = new ZipsId();
        zipsId.setZip(zip);
        zipsId.setState(state);
        zipsId.setCity(city);
        zipsId.setLat(lat);
        zipsId.setLng(lng);

        LOGGER.debug("Deleting Zips with id: {}" , zipsId);
        Zips zips = zipsService.delete(zipsId);

        return zips != null;
    }


    /**
     * @deprecated Use {@link #findZips(String, Pageable)} instead.
     */
    @Deprecated
    @ApiOperation(value = "Returns the list of Zips instances matching the search criteria.")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Zips> searchZipsByQueryFilters( Pageable pageable, @RequestBody QueryFilter[] queryFilters) {
        LOGGER.debug("Rendering Zips list");
        return zipsService.findAll(queryFilters, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Zips instances matching the optional query (q) request param. If there is no query provided, it returns all the instances. Pagination & Sorting parameters such as page& size, sort can be sent as request parameters. The sort value should be a comma separated list of field names & optional sort order to sort the data on. eg: field1 asc, field2 desc etc ")
    @RequestMapping(method = RequestMethod.GET)
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Zips> findZips(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Zips list");
        return zipsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns the paginated list of Zips instances matching the optional query (q) request param. This API should be used only if the query string is too big to fit in GET request with request param. The request has to made in application/x-www-form-urlencoded format.")
    @RequestMapping(value="/filter", method = RequestMethod.POST, consumes= "application/x-www-form-urlencoded")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Page<Zips> filterZips(@ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
        LOGGER.debug("Rendering Zips list");
        return zipsService.findAll(query, pageable);
    }

    @ApiOperation(value = "Returns downloadable file for the data matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
    @RequestMapping(value = "/export/{exportType}", method = {RequestMethod.GET,  RequestMethod.POST}, produces = "application/octet-stream")
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
    public Downloadable exportZips(@PathVariable("exportType") ExportType exportType, @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query, Pageable pageable) {
         return zipsService.export(exportType, query, pageable);
    }

	@ApiOperation(value = "Returns the total count of Zips instances matching the optional query (q) request param. If query string is too big to fit in GET request's query param, use POST method with application/x-www-form-urlencoded format.")
	@RequestMapping(value = "/count", method = {RequestMethod.GET, RequestMethod.POST})
    @WMAccessVisibility(value = AccessSpecifier.APP_ONLY)
	public Long countZips( @ApiParam("conditions to filter the results") @RequestParam(value = "q", required = false) String query) {
		LOGGER.debug("counting Zips");
		return zipsService.count(query);
	}


    /**
	 * This setter method should only be used by unit tests
	 *
	 * @param service ZipsService instance
	 */
	protected void setZipsService(ZipsService service) {
		this.zipsService = service;
	}

}

