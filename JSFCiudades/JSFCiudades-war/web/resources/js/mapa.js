/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 function loadMap (coordX, coordY) {
                var latlng = new google.maps.LatLng(coordX,coordY);
                var myOptions = {
                    zoom: 16,
                    center: latlng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                var map = new google.maps.Map(document.getElementById("map_container"), myOptions);

                var marker = new google.maps.Marker({
                    position: latlng,
                    map: map
                });

            }