package com.pureilab.model.master;

import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Julian on 9/28/2016.
 */
@Transactional
//public interface CountryDao extends CrudRepository<Country, Long> {
public interface CountryDao extends PagingAndSortingRepository<Country, Long> {
//public interface CountryDao extends JpaRepository<Country, Long> {

    public Country findByName(String name);

    public Country findByCode(String code);


}



