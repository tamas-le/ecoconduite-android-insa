% Nettoyage du workspace
clc
clear all
clf

M1 = csvread('data.csv',1,1);
M2 = csvread('data2.csv',1,1);

t1 = M1(:,2);t2 = M2(:,2); % en secondes
v1= M1(:,6);v2= M2(:,6); % en m/s

subplot(1,2,1), plot(t1,v1);
subplot(1,2,2), plot(t2,v2);


figure(2);

 tzoom = t1(725:835);
 vzoom = v1(725:835);
%zoom 2
tzoom3 = t2(3066:3170);
vzoom3 = v2(3066:3170);

 plot(tzoom,vzoom,'.')
    hold on
    pp=polyfit(tzoom,vzoom,5);
    approx=polyval(pp,tzoom);
    plot(tzoom,approx,'r')
    xlabel('temps en secondes') % x-axis label
    ylabel('vitesse en km/h') % y-axis label
    title('Profil d''arret')

figure(5)
    plot(tzoom3,vzoom3,'.')
    hold on
    pp3=polyfit(tzoom3,vzoom3,5);
    approx3=polyval(pp3,tzoom3);
    plot(tzoom3,approx3,'r')
     xlabel('temps en secondes') % x-axis label
ylabel('vitesse en km/h') % y-axis label
title('Profil de ralentissement')