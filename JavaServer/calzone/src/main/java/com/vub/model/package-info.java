/**
 * <p>Models are automatically stored in the database with Hibernate and Jpa. Therefore, all of the model properties should
 * be properly annotated in order to construct the right database layout. This is very important!
 * When adding a model, also add it in persistance.xml</p>
 * 
 * <p>The models are an in-memory representation of the values stored in the database.
 * This means they SHOULD NOT contain any business logic, since this is contained within the associated *Service classes.
 * Example: a Room has certain properties: a name, building and floor. The model contains fields to store these properties.
 * The model should not contain a method which returns a "display name", which could be "building.floor.name".
 * The model doesn't care, but the service layer does.
 */

package com.vub.model;