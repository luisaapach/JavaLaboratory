# Laboratorul 11

# Exercitiul 1 - Security features

   - Autentificare folosind JDBC Realm
        - A fost restructurata aplicatia astfel incat sa includa o noua tabela pentru Grupuri (Entitate, EJB, Relatia Many-to-Many intre Groups si Users - un tabel separat de asociere group-user)
        - A fost configurat noul Security Realm in Glassfish (sa lucreze cu noua tabela de asociere)
        - Au fost definite rolurile (security-roles) in web.xml - admin, guest
        - Au fost puse paginile restrictionate in foldere separate in functie de rol, astfel incat sa securizam in web.xml in functie de urlpattern (ex. /admin/*) si sa renuntam la Filtre
        - LoginBean a fost configurat sa foloseasca noul SRealm
        - request.login este apelat la fiecare apel de login, mai apoi urmand stocarea in sesiune a rolului curent
   - Securizarea de web services prin web constraints 
        - In web.xml s-au definit security constraints 
        - ex. ViewDocumentService (JaxWS) a fost configurat sa ofere acces doar user-ilor cu rol de admin (pe url_pattern /ViewDocumentService)
   - Securizare EJB & REST Services
        - EJB Services
            - UploadService = EJB de management a Upload-urilor - addUpload este restricationata utilizatorilor de tip guest ; getUploads a fost restrictionata pt admin ; updateUpload - este permisa pentru ambele roluri
            - GroupService & UserService - au fost adnotate cu @PermitAll 
        - Au fost securitazate serviciile de tip REST folosind Adnotarile specifice
            - Adnotari peste intreg serviciul si pe actiune folosindu-se @RolesAllowed 
            - view_document este permis doar pentru admin, insa update_document este permis pentru ambele roluri: guest&admin
            