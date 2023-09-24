package com.restapi.restapi.service;
import com.restapi.restapi.entity.EntryEntity;
import com.restapi.restapi.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class EntryService {

//    Check date format validation
    private static Pattern datePattern = Pattern.compile("^\\d{2}-\\d{2}-\\d{4}$");

    public  boolean isValidDate(String date){
        if(datePattern.matcher(date).matches()){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            simpleDateFormat.setLenient(false);
            try {
                simpleDateFormat.parse(date);
                return true;
            }catch (ParseException e){
                return false;
            }
        }
        return false;
    }
    @Autowired
    private EntryRepository entryRepository;

    // Create a new load entry
    public EntryEntity createEntry(EntryEntity entry) {
        if(entry.getLoadingPoint() != null && !entry.getLoadingPoint().isEmpty()){

            if(entry.getUnloadingPoint() != null && !entry.getUnloadingPoint().isEmpty()){

                if(entry.getProductType() != null && !entry.getProductType().isEmpty()){

                    if(entry.getTruckType() != null && !entry.getTruckType().isEmpty()){

                        if(entry.getNoOfTrucks() > 0 ){

                            if(entry.getWeight() > 0 ){

                                if(entry.getShipperId() > 0 ){

                                    if(isValidDate(entry.getDate())){
                                        return entryRepository.save(entry);
                                    }
                                    else{
                                        throw new RuntimeException("Enter the date in this format [DD-MM-YYYY]");
                                    }

                                }
                                else {
                                    throw new RuntimeException("Shipper Id is not found");
                                }

                            }
                            else {
                                throw new RuntimeException("Number of weight value should be positive");
                            }

                        }
                        else {
                            throw new RuntimeException("Number of trucks value should be positive");
                        }

                    }
                    else {
                        throw new RuntimeException("Declare or Initialize the Truck Type");
                    }

                }
                else {
                    throw new RuntimeException("Declare or Initialize the Product Type");
                }

            }
            else {
                throw new RuntimeException("Declare or Initialize the UnLoading Point");
            }

        }
        else{
            throw new RuntimeException("Declare or Initialize the Loading Point");
        }
    }

    // Get Entries by shipperId
    public List<EntryEntity> getAllEntryByShipperId(Long shipperId) {
        return entryRepository.findByshipperId(shipperId);
    }

    // Get Load Entry by ID
    public Optional<EntryEntity> getEntryById(Long id) {
        return entryRepository.findById(id);
    }

    // Update Entry
    public EntryEntity updateEntry(Long id, EntryEntity entryDetails) {
        Optional<EntryEntity> entry = entryRepository.findById(id);
        if (entry.isPresent()) {
            EntryEntity existingEntry = entry.get();

            if(entryDetails.getLoadingPoint() != null && !entryDetails.getLoadingPoint().isEmpty()){
                existingEntry.setLoadingPoint(entryDetails.getLoadingPoint());

                if(entryDetails.getUnloadingPoint() != null && !entryDetails.getUnloadingPoint().isEmpty()){
                    existingEntry.setUnloadingPoint(entryDetails.getUnloadingPoint());

                    if(entryDetails.getProductType() != null && !entryDetails.getProductType().isEmpty()){
                        existingEntry.setProductType(entryDetails.getProductType());

                        if(entryDetails.getTruckType() != null && !entryDetails.getTruckType().isEmpty()){
                            existingEntry.setTruckType(entryDetails.getTruckType());

                            if(entryDetails.getNoOfTrucks() > 0 ){
                                existingEntry.setNoOfTrucks(entryDetails.getNoOfTrucks());

                                if(entryDetails.getWeight() > 0 ){
                                    existingEntry.setWeight(entryDetails.getWeight());
                                    existingEntry.setComment(entryDetails.getComment());

                                    if(isValidDate(entryDetails.getDate())){
                                        existingEntry.setDate(entryDetails.getDate());
                                        return entryRepository.save(existingEntry);
                                    }
                                    else {
                                        throw new RuntimeException("Enter the date in this format [DD-MM-YYYY]");
                                    }

                                }
                                else {
                                    throw new RuntimeException("Number of weight value should be positive");
                                }

                            }
                            else {
                                throw new RuntimeException("Number of trucks value should be positive");
                            }

                        }
                        else {
                            throw new RuntimeException("Declare or Initialize the Truck Type");
                        }

                    }
                    else {
                        throw new RuntimeException("Declare or Initialize the Product Type");
                    }

                }
                else {
                    throw new RuntimeException("Declare or Initialize the UnLoading Point");
                }

            }
            else{
                throw new RuntimeException("Declare or Initialize the Loading Point");
            }


        }
        return null;
    }

    // Delete Entry
    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }

}