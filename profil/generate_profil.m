
% Nettoyage du workspace
clc
clear all
clf

% Importation des données 

M1 = csvread('data.csv',1,1);
M2 = csvread('data2.csv',1,1);

% Génération des vecteurs à partir des données
    t1 = M1(:,2);
    v1= M1(:,6);
    
    t2 = M2(:,2);
    v2= M2(:,6);
    
% Vitesse en fonction du temps
    
    figure(1)
    plot(t1,v1)
    
    figure(2)
    plot(t2,v2)
    
% Zoom sur la première zone interessante     
    
    tzoom = t1(725:840);
    vzoom = v1(725:840);

    figure(3)
    plot(tzoom,vzoom,'.')
    hold on
    pp=polyfit(tzoom,vzoom,5);
    approx=polyval(pp,tzoom);
    plot(tzoom,approx)
    
% Zoom sur la seconde zone interessante

tzoom2 = t1(1184:1274);
vzoom2 = v1(1184:1274);

    figure(4)
    plot(tzoom2,vzoom2,'.')
    hold on
    pp2=polyfit(tzoom2,vzoom2,5);
    approx2=polyval(pp2,tzoom2);
    plot(tzoom2,approx2)
    
    
    
 % Derniere zone interessante
 
 
 tzoom3 = t2(3066:3175);
vzoom3 = v2(3066:3175);

    figure(5)
    plot(tzoom3,vzoom3,'.')
    hold on
    pp3=polyfit(tzoom3,vzoom3,5);
    approx3=polyval(pp3,tzoom3);
    plot(tzoom3,approx3)
    
    
    figure(6)
    test=polyval(pp3,t2);
    plot(t2,test)
    
    syms t
    
    profil1 = poly2sym(pp,t)
    profil2 = poly2sym(pp3,t)
    
    inverse1 = finverse(profil1)
    inverse2 = finverse(profil2)
    


    

   


    








