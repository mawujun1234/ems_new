//     Zepto.js
//     (c) 2010-2016 Thomas Fuchs
//     Zepto.js may be freely distributed under the MIT license.

;(function($){
  var touch = {},
    touchTimeout, tapTimeout, swipeTimeout, longTapTimeout,
    longTapDelay = 750,
    moveDistance=30,
    gesture

  function swipeDirection(x1, x2, y1, y2) {
    return Math.abs(x1 - x2) >=
      Math.abs(y1 - y2) ? (x1 - x2 > moveDistance ? 'Left' : 'Right') : (y1 - y2 > 0 ? 'Up' : 'Down')
  }


  $(document).ready(function(){
    var now, delta, deltaX = 0, deltaY = 0, firstTouch, _isPointerType

      $(document).on('mousedown',function(e){
        touch.el=$(e.target);
        touch.e=e;
        touch.x1=e.clientX;
        touch.y1=e.clientY;
      })
      .on('mouseup',function(e){
        touch.x2=e.clientX;
        touch.y2=e.clientY;
        //alert(swipeDirection(touch.x1,touch.x2,touch.y1,touch.y2));
        //console.log("mouseup:"+swipeDirection(touch.x1,touch.x2,touch.y1,touch.y2));
        //console.log(touch.el.attr("id"));
        touch.el.trigger('slide'+ swipeDirection(touch.x1,touch.x2,touch.y1,touch.y2))
      });
  });

  ;['slideLeft', 'slideRight', 'slideUp', 'slideDown'].forEach(function(eventName){
    $.fn[eventName] = function(callback){ return this.on(eventName, callback) }
  })
})(Zepto)
