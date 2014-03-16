/**
 * <p> This package contains all of the services which can be associated with the models in com.vub.models. 
 * These services contain all of the business logic, and contain a reference to the Database Access Objects.
 * Although the services mostly "copy" the CRUD operations the DAO's provide (forwarding the calls to the DAO's),
 * it is an important separation. The DAO's cannot contain any business logic, and do not care about the meaning of the data </p>
 * 
 */

package com.vub.service;