Annotation processor for models annotated with {{@Document}}
============================================================

This is annotation processor to be used with [spring-data-mongodb](http://www.springsource.org/spring-data/mongodb). It generates meta model for all classes annotated with @Document. This enables creating type safe queries.

Why QueryDSL for MongoDB is not sufficient? 
-------------------------------------------
- QueryDSL can be used only in queries. What about updating and deleting data?
- QueryDSL comes with a bunch of dependencies. Do we really need them? Our need is simple - just generate meta model in order to replace string queries.
- In application that uses spring-data-mongodb, QueryDSL is a kind of a second (duplicated) way of querying data. I know that QueryDSL predicates are quite handy but I would like all project queries to use the same mechanism. Moreover sytax used in pure spring-data-mongodb is much more similar to MongoDB shell syntax.
- How can I use QueryDSL mechanism with plain MonogoTemplate?
- QueryDSL have some minor problems with querying arrays of nested documents in QueryDSL. For example it's impossible to create such a query: {{db.inventory.find( { 'memos.0.by': 'shipping' } )}} (see MongoDB doc: [match-a-field-in-the-subdocument-using-the-array-index|http://docs.mongodb.org/manual/tutorial/query-documents/#match-a-field-in-the-subdocument-using-the-array-index])

Maybe some of these arguments come from my lack of knowledge. Maybe I didn't study the documentation as hard as I should. If I am wrong please correct me.

Spring feature
--------------
This annotation processor is proposed as feature on (Springsource Jira)[https://jira.springsource.org/browse/DATAMONGO-744].

Projects
--------
This project is separated into projects:

		spring-data-mongodb-processor-parent
		   |- spring-data-mongodb-processor-example
		   |- spring-data-mongodb-processor

- `spring-data-mongodb-processor-parent` - Just groups sub modules.
- `spring-data-mongodb-processor-example` - It is contains test cases and an example of how to use `spring-data-mongodb-processor`.
- `spring-data-mongodb-processor` - Contains source for annotation processor.

Usage example
-------------

Using this annotation processor you can create string-free queries like:

		// Simple fields:		
		Criteria.where(User_.email).is(email);
		// Instead of: Criteria.where("user.email").is(email);

		// Nested documents:	
		Criteria.where(User_.details.address).is(address);
		// Instead of: Criteria.where("user.details.address").is(address);

		// Arrays:	
		Criteria.where(User_.settings.index(5));
		// Instead of: Criteria.where("user.settings.5");

