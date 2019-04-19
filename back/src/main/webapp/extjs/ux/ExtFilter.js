/**
解决ext chart 保存中文乱码
*/
Ext.draw.Surface.save= function(surface, config) {
        config = config || {};
        var exportTypes = {
                'image/png': 'Image',
                'image/jpeg': 'Image',
                'image/svg+xml': 'Svg'
            },
        prefix = exportTypes[config.type] || 'Svg',
        exporter = Ext.draw.engine[prefix + 'Exporter'];    
		exporter.defaultUrl='chart/saveChart.do'; 
        return exporter.generate(surface, config);
            
};