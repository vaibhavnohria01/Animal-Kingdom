package com.g12.faunalencyclopedia;

import java.util.List;

public class Animal {
    String county;
    String category;
    String taxonomic_group;
    String taxonomic_subgroup;
    String scientific_name;
    String common_name;
    String year_last_documented;
    String ny_listing_status;
    String federal_listing_status;
    String state_conservation_rank;
    String global_conservation_rank;
    String distribution_status;

    public Animal(List<String> datalist){
        this.county = datalist.get(8);
        this.category = datalist.get(9);
        this.taxonomic_group = datalist.get(10);
        this.taxonomic_subgroup = datalist.get(11);
        this.scientific_name = datalist.get(12);
        this.common_name = datalist.get(13);
        this.year_last_documented = datalist.get(14);
        this.ny_listing_status = datalist.get(15);
        this.federal_listing_status = datalist.get(16);
        this.state_conservation_rank = datalist.get(17);
        this.global_conservation_rank = datalist.get(18);
        this.distribution_status = datalist.get(19);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "county='" + county + '\'' +
                ", category='" + category + '\'' +
                ", taxonomic_group='" + taxonomic_group + '\'' +
                ", taxonomic_subgroup='" + taxonomic_subgroup + '\'' +
                ", scientific_name='" + scientific_name + '\'' +
                ", common_name='" + common_name + '\'' +
                ", year_last_documented=" + year_last_documented +
                ", ny_listing_status='" + ny_listing_status + '\'' +
                ", federal_listing_status='" + federal_listing_status + '\'' +
                ", state_conservation_rank='" + state_conservation_rank + '\'' +
                ", global_conservation_rank='" + global_conservation_rank + '\'' +
                ", distribution_status='" + distribution_status + '\'' +
                '}';
    }
}
