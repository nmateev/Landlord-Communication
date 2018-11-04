package com.wasp.landlordcommunication.diconfig;

import com.wasp.landlordcommunication.models.ChatSession;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.CacheRepository;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;
import com.wasp.landlordcommunication.repositories.base.PaymentsRepository;
import com.wasp.landlordcommunication.repositories.base.PropertiesRepository;
import com.wasp.landlordcommunication.repositories.base.RatingsRepository;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import com.wasp.landlordcommunication.services.HttpChatSessionsService;
import com.wasp.landlordcommunication.services.HttpPaymentsService;
import com.wasp.landlordcommunication.services.HttpPropertiesService;
import com.wasp.landlordcommunication.services.HttpRatingsService;
import com.wasp.landlordcommunication.services.HttpUsersService;
import com.wasp.landlordcommunication.services.base.ChatSessionsService;
import com.wasp.landlordcommunication.services.base.PaymentsService;
import com.wasp.landlordcommunication.services.base.PropertiesService;
import com.wasp.landlordcommunication.services.base.RatingsService;
import com.wasp.landlordcommunication.services.base.UsersService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {
    @Provides
    public UsersService usersService(UsersRepository usersRepository, CacheRepository<User> usersCacheRepository) {
        return new HttpUsersService(usersRepository, usersCacheRepository);
    }

    @Provides
    public RatingsService ratingsService(RatingsRepository ratingsRepository) {
        return new HttpRatingsService(ratingsRepository);
    }

    @Provides
    public PaymentsService paymentsService(PaymentsRepository paymentRepository) {
        return new HttpPaymentsService(paymentRepository);
    }

    @Provides
    public PropertiesService propertiesService(PropertiesRepository propertiesRepository, CacheRepository<Property> propertiesCacheRepository) {
        return new HttpPropertiesService(propertiesRepository, propertiesCacheRepository);
    }

    @Provides
    public ChatSessionsService chatSessionsService(ChatSessionsRepository chatSessionsRepository) {
        return new HttpChatSessionsService(chatSessionsRepository);
    }
}
