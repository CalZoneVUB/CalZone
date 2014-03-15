/**
 * <p>Whenever a new object is defined in the model, it should be added to the sessionFactory bean, defined in:
 * src/main/webapp/WEB-INF/applicationContext.xml. This bean contains an "annotatedClasses" property 
 * which contains a list of all models which have been annotated to be stored in the database with Hibernate.</p>
 * 
 * <p>The models are an in-memory representation of the values stored in the database.
 * This means they SHOULD NOT contain any business logic, since this is contained within the associated *Service classes.
 * Example: a Room has certain properties: a name, building and floor. The model contains fields to store these properties.
 * The model should not contain a method which returns a "display name", which could be "building.floor.name".
 * The model doesn't care, but the service layer does.
 */

package com.vub.model;