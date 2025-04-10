package com.cankus.repository;

import com.cankus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // JpaRepository, CRUD işlemleri için gerekli temel metotları içerir.
    // Özel sorgular burada tanımlanabilir.

    // Örneğin, kullanıcı adı ile arama yapalım:
    //User findByUserName(String userName);

    // Kullanıcı adına göre arama (username benzersiz olduğu için Optional döner)
  //  Optional<User> findByUserName(String userName);


}


/*  controllerda getCreatePage ile tüm user listi için
1- repository --> list için default methodlar yeterli
2- service interface içinde listeleme methodu gerek
3-serviceimpl -->  find all method oluşturulacak
4-UserMapper-->findAll method için userMapper içinde ModelMapper objesi oluşturmak gerek
5-Main method --> modelMapper için Bean annotation ile ModelMapper bean oluştur
6- UserMapper --> private final ModelMapper modelMapper; oluşturuldu ve // DTO -> Entity // Entity -> DTO mthodlar
7-iplementation --> findAll() meythod ve //lambda expression method refrains  çalış
8- userController-->  getCreatePage() metghodu tamamla
 */
