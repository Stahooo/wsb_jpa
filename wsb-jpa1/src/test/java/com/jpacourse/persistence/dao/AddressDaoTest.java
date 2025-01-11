package com.jpacourse.persistence.dao;


import com.jpacourse.persistence.entity.AddressEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AddressDaoTest {
    @Autowired
    private AddressDao addressDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testOptimisticLockingError() {
        // given
        Long addressId = 1L;
        AddressEntity address1 = addressDao.findOne(addressId);
        entityManager.clear();
        AddressEntity address2 = addressDao.findOne(addressId);
        Long addressVersion = address1.getVersion();
        assertThat(address2.getVersion()).isEqualTo(addressVersion);

        //when
        address1.setAddressLine1("test");
        address2.setAddressLine1("test2");
        addressDao.update(address2);
        assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
            addressDao.update(address1);
        });

        //then
        AddressEntity finalAddress = addressDao.findOne(addressId);
        assertThat(finalAddress.getAddressLine1()).isEqualTo("test2");
        assertThat(finalAddress.getVersion()).isEqualTo(addressVersion+1);
    }
}
