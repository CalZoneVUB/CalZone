/**
 * From <a href="http://www.ibm.com/developerworks/java/library/j-genericdao/index.html">IBM article</a>
 * <ul>
 * <li>All database access in the system is made through a DAO to achieve encapsulation.</li>
 * <li>Each DAO instance is responsible for one primary domain object or entity. If a domain object has an independent lifecycle, it should have its own DAO.</li>
 * <li>The DAO is responsible for creations, reads (by primary key), updates, and deletions -- that is, CRUD -- on the domain object.</li>
 * <li>The DAO may allow queries based on criteria other than the primary key. I refer to these as finder methods or finders. The return value of a finder is normally a collection of the domain object for which the DAO is responsible.</li>
 * <li>The DAO is not responsible for handling transactions, sessions, or connections. These are handled outside the DAO to achieve flexibility.</li>
 * </ul>
 */

package com.vub.dao;