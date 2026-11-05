package org.main.services;

import org.main.model.Transaction;

import java.io.BufferedReader;

public interface ITransaction {
    double VAT = 0.12;
    Transaction createTransaction(BufferedReader br);
}

