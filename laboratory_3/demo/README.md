# Laboratorul 3

## Web Filters and Wrappers
 - `LogFilter` - display logs for input.jsp & result.jsp pages
 - `ValidationFilter`
	- if word or definition is missing => forward to input.jsp page
	- else if language is empty, get the default Language value
		from context init parameter (getInitParameter("defaultLanguage"))
		using a HTTPServletRequestWrapper
 - `ResponseFilter` and `ResponseWrapper`
	- parse the HTML document from the servletResult
	- decorates all the \<h1\> tags with a new style (border,background and textalign)

## Custum JSP Tags
 - `tag_definition.tld` file for specifying the parameters and the TagHandler Class
 - `DefinitionTag.java` TagHandler Class - which prints the definition of the specified word
	 (with the specified language if defined)

## Implement Tags using JSTL
 - `viewDictionary.tag`
	- where is specified the optional parameter "language" (using <%@ attribute >)
	- a table is written by iterating through all the elements specified inside the Session attribute :
		`sessionScope.Controller_Dictionary` and filtering them by language

## Internationalize and Localize
 - A new field was added to DictionaryPair class with the current Date specific to the client's locale
 - fmt tag was used for Internationalization 
	- I've constructed the classes for RO and EN locales : `Messages_ro.java` & `Messages_en.java`
	- I've also tried the implementation through Resourse Bundle Properties files, but finally 
		i've choose to set the fmt Bundle basename to the ListResourceBundle classes
	