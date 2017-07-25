package com.lmsbatch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
        final Person transformedPerson = new Person(person.getShrStaffID(), person.getPreferredLastName(), person.getPreferredFirstName(),
                person.getConflictGroup(), person.getEmployeeCode(),person.getBusinessUnit(), person.getState(),person.getTeam(), person.getManagerReference(),
                person.getSecondLevelManager(), person.getRoleLocation(), person.getEmailAddressWork(), person.getActive());

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}
