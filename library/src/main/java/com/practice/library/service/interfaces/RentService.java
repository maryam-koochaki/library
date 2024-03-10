package com.practice.library.service.interfaces;

import com.practice.library.dto.RentRequestDTO;
import com.practice.library.model.Rent;
import exception.MyException;

import java.util.List;

public interface RentService {
    Rent rentBook(RentRequestDTO rentDTO) throws MyException;
    boolean returnBook(long rentId) throws MyException;
    List<Rent> rentBooks();
}
