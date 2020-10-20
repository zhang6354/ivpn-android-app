package net.ivpn.client.vpn.controller;

/*
 IVPN Android app
 https://github.com/ivpn/android-app
 <p>
 Created by Oleksandr Mykhailenko.
 Copyright (c) 2020 Privatus Limited.
 <p>
 This file is part of the IVPN Android app.
 <p>
 The IVPN Android app is free software: you can redistribute it and/or
 modify it under the terms of the GNU General Public License as published by the Free
 Software Foundation, either version 3 of the License, or (at your option) any later version.
 <p>
 The IVPN Android app is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 details.
 <p>
 You should have received a copy of the GNU General Public License
 along with the IVPN Android app. If not, see <https://www.gnu.org/licenses/>.
*/

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import net.ivpn.client.IVPNApplication;
import net.ivpn.client.vpn.Protocol;
import net.ivpn.client.vpn.ProtocolController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

public class WireGuardKeyBroadcastReceiver extends BroadcastReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(WireGuardKeyBroadcastReceiver.class);

    @Inject
    public ProtocolController protocolController;
    @Inject
    public VpnBehaviorController vpnBehaviorController;

    @Override
    public void onReceive(Context context, Intent intent) {
        IVPNApplication.getApplication().appComponent.provideActivityComponent().create().inject(this);
        LOGGER.info("Receive intent");
        if (protocolController.getCurrentProtocol().equals(Protocol.WIREGUARD)
                && vpnBehaviorController.isVPNActive()) {
            vpnBehaviorController.regenerate();
        }
    }
}