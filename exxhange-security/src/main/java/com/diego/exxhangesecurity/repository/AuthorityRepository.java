package com.diego.exxhangesecurity.repository;

import com.diego.exxhangesecurity.entity.Authority;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long>{

    Authority findByName(String name);

}