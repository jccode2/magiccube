
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <title>BubbleTip Demo</title>
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js" type="text/javascript"></script>
	
	<script src="js/jQuery.bubbletip-1.0.6.js" type="text/javascript"></script>
	<link href="js/bubbletip/bubbletip.css" rel="stylesheet" type="text/css" />
	<!--[if IE]>
	<link href="js/bubbletip/bubbletip-IE.css" rel="stylesheet" type="text/css" />
	<![endif]-->
	
	<style type="text/css">
		p, h4, li
		{
			font-family: Arial, Tahoma, Verdana;
		}
		pre.tip
		{
			margin: 0px;
			padding: 5px;
			font-size: 0.9em;	
		}
		pre.code
		{
			margin: 0px;
			padding: 20px;
			border: solid 1px #CCC;
			font-size: 1.0em;
		}
		em 
		{
			font-size: 0.9em;
			color: #777777;
		}
	</style>
	
	<script type="text/javascript">
		$(window).bind('load', function() {
			$('#a1_up').bubbletip($('#tip1_up'));

			$('#a1_down').bubbletip($('#tip1_down'), {
				deltaDirection: 'down',
				deltaPosition: 50,
				offsetTop: 20
			});
			$('#a1_left').bubbletip($('#tip1_left'), {
				deltaDirection: 'left',
				animationDuration: 100,
				offsetLeft: -20
			});
			$('#a1_right').bubbletip($('#tip1_right'), { deltaDirection: 'right' });
			$('#a1_trigger').bubbletip($('#tip1_trigger1'), { positionAtElement: $('#a1_target') });

			$('#a_unbind').bubbletip($('#tip1_trigger2_unbind'));
			$('#a_unbind').bind('click', function(event) {
				$('#a1_trigger').removeBubbletip($('#tip1_trigger2'));
				event.preventDefault();
			});
			$('#a_bind').bubbletip($('#tip1_trigger2_bind'));
			$('#a_bind').bind('click', function(event) {
				$('#a1_trigger').bubbletip($('#tip1_trigger2'), {
					positionAtElement: $('#a1_target'),
					deltaDirection: 'down',
					delayShow: 500,
					delayHide: 1000
				});
				event.preventDefault();
			});

			$('#inpText').bubbletip($('#tip1_focusblur'), {
				deltaDirection: 'right',
				bindShow: 'focus',
				bindHide: 'blur'
			});
			$('#a2_trigger').bubbletip($('#tip2_multi'), { positionAtElement: $('#a2_target') });
			$('.a2').bind('mouseover', function() { $('#a2_trigger').trigger('mouseover'); });
			$('.a2').bind('mouseout', function() { $('#a2_trigger').trigger('mouseout'); });
			
		});
	</script>
</head>
<body>
	<div id="bubbletipanchor"></div>
	<h4>bubbletip</h4>
	<p>A jQuery plugin to create a bubble-stylized tooltip popup</p>
	
	<h4>download</h4>
	<p><a href="http://code.google.com/p/bubbletip/">http://code.google.com/p/bubbletip/</a></p>
	
	<h4>features</h4>
	<ul>
	<li>multiple tips on a page</li>
	<li>multiple tips per jQuery element</li>
	<li>tips open outward in four directions:
		<ul>
		<li>up</li>
		<li>down</li>
		<li>left</li>
		<li>right</li>
		</ul>
	</li>
	<li>tips can be: 
		<ul>
		<li>anchored to the triggering jQuery element</li>
		<li>absolutely positioned</li>
		<li>opened at the current mouse coordinates</li>
		<li>anchored to a specified jQuery element</li>
		</ul>
	</li>
	<li>IE png transparency is handled via filters</li>
	</ul> 
	
<h4>examples</h4>
<p>MOUSEOVER to open a tooltip 
	<a id="a1_up" href="#">above</a>, 
	<a id="a1_down" href="#">below</a>, 
	<a id="a1_left" href="#">to the left</a> or 
	<a id="a1_right" href="#">to the right</a> of any element.</p>
<p>MOUSEOVER a 
	<a id="a1_trigger" href="#">trigger element</a> [ <a href="#" id="a_bind">bind</a> / <a href="#" id="a_unbind">unbind</a> ] to open a tooltip above and below a 
	<a id="a1_target" href="#">target element</a>.</p>
<p>MOUSEOVER any <a href="#" id="a2_trigger">trigger</a>, <a href="#" class="a2">trigger</a>, <a href="#" class="a2">trigger</a>, <a href="#" class="a2">trigger</a>
	to open a tip on a <a href="#" id="a2_target">target</a>.</p>
<p>FOCUS & BLUR <input type="text" id="inpText" value="focus me!" /></p>


<div id="tip1_up" style="display:none;"><pre class="tip">{ deltaDirection: 'up' }</pre></div>
<div id="tip1_down" style="display:none;">
<pre class="tip">{ 
    deltaDirection: 'down', 
    deltaPosition: 100,
    offsetTop: 10 
}</pre>
</div>
<div id="tip1_left" style="display:none;">
<pre class="tip">{ 
    deltaDirection: 'left', 
    animationDuration: 100,
    offsetLeft: -20 
}</pre>
</div>
<div id="tip1_right" style="display:none;"><img src="bubble.jpg" width="128" height="128" /></div>
<div id="tip1_trigger1" style="display:none;">
<pre class="tip">{ 
    deltaDirection: 'up', 
    positionAtElement: $('#a1_target')
}</pre>
</div>
<div id="tip1_trigger2" style="display:none;">
<pre class="tip">{ 
    deltaDirection: 'down', 
    positionAtElement: $('#a1_target')
    delayShow: 500,
    delayHide: 1000			
}</pre>
</div>
<div id="tip1_trigger2_bind" style="display:none;">
<pre class="tip">click to bind a second bubbletip 
to the trigger element

$('#a1_trigger').bubbletip($('#tip1_trigger2'), {
    positionAtElement: $('#a1_target'),
    deltaDirection: 'down',
    delayShow: 500,
    delayHide: 1000
});</pre>
</div>
<div id="tip1_trigger2_unbind" style="display:none;">
<pre class="tip">click to unbind the second bubbletip

$('#a1_trigger').removeBubbletip($('#tip1_trigger2'));
</pre>
</div>
<div id="tip1_focusblur" style="display:none;">
<pre class="tip">{
    deltaDirection: 'right',
    bindShow: 'focus',
    bindHide: 'blur'
}</pre>	
</div>
<div id="tip2_multi" style="display:none;">
<pre class="tip">bubbletip
bubbletip
bubbletip
bubbletip</pre>	
</div>
<h4>usage</h4>
<p>the "trigger" element is the element upon which the bubbletip is instantiated:</p>
<pre class="code">&lt;a id=&quot;a1_up&quot; href=&quot;#&quot;&gt;above&lt;/a&gt;</pre>
<p>the "tip" element should be hidden (display:none) and contain the contents of the tip to be displayed:</p>
<pre class="code">&lt;div id=&quot;tip1_up&quot; style=&quot;display:none;&quot;&gt;&lt;pre class=&quot;tip&quot;&gt;{ deltaDirection: 'up' }&lt;/pre&gt;&lt;/div&gt;</pre>
<p>the javascript to create the bubbletip:</p>
<pre class="code">&lt;script type=&quot;text/javascript&quot;&gt;
    $(window).bind('load', function() {
        $('#a1_up').bubbletip($('#tip1_up'));
    });
&lt;/script&gt;</pre>
<p>the javascript to remove all tips from the trigger element:</p>
<pre class="code">$('#a1_up').removeBubbletip();</pre>
<p>the javascript to remove an array of specified tips from the trigger element:</p>
<pre class="code">$('#a1_up').removeBubbletip([$('#tip1_up'), ...]);</pre>

<h4>options</h4>
<ul>
<li><strong>positionAt:</strong> element(default) | body | mouse <em>» the tip location type</em></li>
<li><strong>positionAtElement:</strong> <em>» the element to anchor the tip at (e.g. $('#target')); leave blank to anchor to the trigger; requires positionAt: element; </em></li>
<li><strong>offsetTop:</strong> 0 <em>» offset the vertical position</em></li>
<li><strong>offsetLeft:</strong> 0 <em>» offset the horizontal position</em></li>
<li><strong>deltaPosition:</strong> 30 <em>» the distance the tip will travel during animation</em></li>
<li><strong>deltaDirection:</strong> up(default) | down | left | right <em>» the direction the tip will animate</em></li>
<li><strong>animationDuration:</strong> 250 <em>» the animation duration</em></li>
<li><strong>animationEasing:</strong> swing(default) | linear <em>» standard animation easing</em></li>
<li><strong>bindShow:</strong> mouseover(default) | focus | click | ... <em>» the bind event(s) to show the tip</em></li>
<li><strong>bindHide:</strong> mouseout(default) | blur | ... <em>» the bind event(s) to hide the tip</em></li>
<li><strong>delayShow:</strong> 0 <em>» the time in ms to delay showing the tip</em></li>
<li><strong>delayHide:</strong> 500 <em>» the time in ms to delay hiding the tip</em></li>
<li><strong>calculateOnShow:</strong> false <em>» recalculate the position prior to showing</em></li>
</ul>
</body>
</html>
