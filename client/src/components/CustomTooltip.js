import React from 'react';
import DefaultTooltipContent from 'recharts/lib/component/DefaultTooltipContent';

const CustomTooltipContent = (props) => {
    if (props.payload[0] != null) {
        const newPayload = [
            {
                name: props.name,
                value: props.payload[0].payload.value,
            },
        ];

        return <DefaultTooltipContent {...props} payload={newPayload} />;
    }

return <DefaultTooltipContent {...props} />;
};

export default CustomTooltipContent