package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.Rating;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;
import com.wasp.landlordcommunication.repositories.base.Repository;
import com.wasp.landlordcommunication.services.HttpPaymentsService;
import com.wasp.landlordcommunication.services.HttpPropertiesService;
import com.wasp.landlordcommunication.services.HttpRatingsService;
import com.wasp.landlordcommunication.services.HttpUsersService;
import com.wasp.landlordcommunication.services.base.PaymentsService;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.services.base.UsersService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    public UsersService usersService(Repository<User> usersRepository, CacheRepository<User> cacheRepository) {
        return new HttpUsersService(usersRepository, cacheRepository);
    }

    @Provides
    public RatingsService ratingsService(Repository<Rating> ratingsRepository) {
        return new HttpRatingsService(ratingsRepository);
    }

    @Provides
    public PaymentsService paymentsService(Repository<Payment> paymentRepository) {
        return new HttpPaymentsService(paymentRepository);
    }

    @Provides
    public PropertiesService propertiesService(Repository<Property> propertiesRepository) {
        return new HttpPropertiesService(propertiesRepository);
    }
}
